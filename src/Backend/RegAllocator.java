package Backend;

import ASM.ASMBlock;
import ASM.ASMFunc;
import ASM.ASMRoot;
import ASM.Inst.Arith;
import ASM.Inst.Lw;
import ASM.Inst.Mv;
import ASM.Inst.Sw;
import ASM.Operand.Imm;
import ASM.Operand.PhyReg;
import ASM.Operand.Reg;
import ASM.Operand.VirtualReg;
import org.antlr.v4.runtime.misc.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RegAllocator {

	public ASMRoot root;

	public RegAllocator(ASMRoot root) {
		this.root = root;
	}

	private ASMFunc currentFunc = null;
	private int stackDelta = 0;
	private final int STACK_ALIGN = 16;


	public void work() {
		Reg.regList.forEach(x -> x.color = x);
		K = Reg.colorReg.size();
		for (var x : root.funcs) {
//		root.funcs.forEach(x -> {
			currentFunc = x;
			stackDelta = 0;
			startAlloc();
			System.out.println(x.id + " finished");
//		});
		}
	}

	int K = 0;
	// here linked hashset is much faster (!) than hashset
	// maybe the reason is that we spend much time iterating
	LinkedHashSet<Reg> initial = new LinkedHashSet<>(), simplifyWorklist = new LinkedHashSet<>(),
		freezeWorklist = new LinkedHashSet<>(), spillWorklist = new LinkedHashSet<>(), spilledNodes = new LinkedHashSet<>(),
		coalescedNodes = new LinkedHashSet<>(), coloredNodes = new LinkedHashSet<>();
	LinkedList<Reg> selectStack = new LinkedList<>();

	LinkedHashSet<Mv> coalescedMoves = new LinkedHashSet<>(), constrainedMoves = new LinkedHashSet<>(), frozenMoves = new LinkedHashSet<>(),
		worklistMoves = new LinkedHashSet<>(), activeMoves = new LinkedHashSet<>();

	// vir-vir, vir-phy
	HashMap<Reg, LinkedHashSet<Reg>> adjList = new HashMap<>();
	// phy-vir, vir-phy, vir-vir
	LinkedHashSet<Pair<Reg, Reg>> adjSet = new LinkedHashSet<>();
	HashMap<Reg, AtomicInteger> degree = new HashMap<>();
	HashMap<Reg, LinkedHashSet<Mv>> moveList = new HashMap<>();
	HashMap<Reg, Reg> alias = new HashMap<>();
//	HashMap<Reg, PhyReg> color = new HashMap<>();

	boolean isPrecolored(Reg reg) {
		return reg instanceof PhyReg;
	}

	// phy-phy: judged independently
	// this function judge vir-*
	boolean isConnected(Reg lhs, Reg rhs) { // should be areConnected LOL, but I followed the java naming convention
		return adjSet.contains(new Pair<>(lhs, rhs));
	}

	void addEdge(Reg lhs, Reg rhs) {
		if (lhs != rhs && !isConnected(lhs, rhs)) {
			adjSet.add(new Pair<>(lhs, rhs));
			adjSet.add(new Pair<>(rhs, lhs));
			if (!isPrecolored(lhs)) {
				adjList.get(lhs).add(rhs);
				degree.get(lhs).incrementAndGet();
			}
			if (!isPrecolored(rhs)) {
				adjList.get(rhs).add(lhs);
				degree.get(rhs).incrementAndGet();
			}
		}
	}

	private void startAlloc() {
		init();
		livenessAnalysis();
		build();
		makeWorklist();
		while(!simplifyWorklist.isEmpty() || !worklistMoves.isEmpty() ||
				!freezeWorklist.isEmpty() || !spillWorklist.isEmpty()) {
			if (!simplifyWorklist.isEmpty()) simplify();
			else if (!worklistMoves.isEmpty()) coalesce();
			else if (!freezeWorklist.isEmpty()) freeze();
			else if (!spillWorklist.isEmpty()) selectSpill();
		}
		assignColors();
		if (!spilledNodes.isEmpty()) {
			rewriteProgram();
			startAlloc();
		} else {
			stackDeltaHandle();
			removeUselessInst();
		}
	}

	void clearCollection(Collection<?>... val) {
		for (var i : val) {
			i.clear();
		}
	}

	void clearMap(Map<?, ?>... val) {
		for (var i : val) {
			i.clear();
		}
	}

	void init() {
		clearCollection(initial, simplifyWorklist, freezeWorklist, spillWorklist, spilledNodes,
				coalescedNodes, coloredNodes, selectStack, coalescedMoves, constrainedMoves,
				frozenMoves, worklistMoves, activeMoves, adjSet);
		clearMap(adjList, degree, moveList, alias);

		// init 'initial' and hashmaps
		currentFunc.blocks.forEach(b -> {
			b.insts.forEach(inst -> {
				var use = inst.getUseList();
				use.addAll(inst.getDefList());
				use.forEach(r -> {
					if (r instanceof PhyReg || initial.contains(r)) {
						return;
					}
					r.color = null;
					initial.add(r);
					adjList.put(r, new LinkedHashSet<>());
					degree.put(r, new AtomicInteger());
					moveList.put(r, new LinkedHashSet<>());
				});
			});
		});
		Reg.regList.forEach(r -> {
			degree.put(r, new AtomicInteger(100000000));
			moveList.put(r, new LinkedHashSet<>());
		});
	}

	// val, multiple > 0
	int roundUp(int val, int multiple) {
		var reminder = val % multiple;
		return reminder == 0 ? val : val - reminder + multiple;
	}

	void stackDeltaHandle() {
		stackDelta += root.callArgStackDelta;
		// align stack length to multiple of 16
		stackDelta = roundUp(stackDelta, STACK_ALIGN);

		// first inst: addi sp, sp, -stackDelta
		var inst = currentFunc.blocks.getFirst().insts.getFirst();
		inst.setImmVal(-stackDelta);

		currentFunc.blocks.forEach(b -> b.insts.forEach(i -> {
			if (i.isUndetermined()) {
				i.setImmVal(stackDelta - i.getImmVal());
			}
		}));

		// -2 inst: addi sp, sp, stackDelta
		var it = currentFunc.blocks.getLast().insts.descendingIterator();
		it.next();
		inst = it.next();
		inst.setImmVal(stackDelta);
	}

	void removeUselessInst() {
		currentFunc.blocks.forEach(b -> {
			for (var iter = b.insts.listIterator(); iter.hasNext();) {
				var inst = iter.next();
				if (inst instanceof Mv && inst.rs1.equals(inst.rd)) {
					iter.remove();
				} else if (inst instanceof Arith newInst && newInst.op.equals("add") &&
						newInst.rs1.equals(newInst.rd) &&
						newInst.imm instanceof Imm imm && imm.value == 0) {
					iter.remove();
				}
			}
		});
	}

	HashMap<ASMBlock, LinkedHashSet<Reg>> blkDef = new HashMap<>(), blkUse = new HashMap<>();
	HashMap<ASMBlock, LinkedHashSet<Reg>> blkLiveIn = new HashMap<>(), blkLiveOut = new HashMap<>();
	void livenessAnalysis() {
		blkDef.clear();
		blkUse.clear();
		blkLiveIn.clear();
		blkLiveOut.clear();

		var queue = new LinkedList<ASMBlock>();
		var visit = new LinkedHashSet<ASMBlock>();
		currentFunc.blocks.forEach(x -> {
			LinkedHashSet<Reg> use = new LinkedHashSet<>(), def = new LinkedHashSet<>();
			x.insts.forEach(y -> {
				var instUse = y.getUseList();
				instUse.removeAll(def);
				use.addAll(instUse);
				def.addAll(y.getDefList());
			});
			blkUse.put(x, use);
			blkDef.put(x, def);
			blkLiveIn.put(x, new LinkedHashSet<>());
			blkLiveOut.put(x, new LinkedHashSet<>());

			if (x.nxt.isEmpty()) {
				queue.push(x);
				visit.add(x);
			}
		});

		// spfa or the work-list algorithm
		while (!queue.isEmpty()) {
			var x = queue.poll();
			visit.remove(x);

			// update using the data flow equation
			var liveOut = new LinkedHashSet<Reg>();

			x.nxt.forEach(y -> liveOut.addAll(blkLiveIn.get(y)));
			blkLiveOut.replace(x, liveOut);

			var liveIn = new LinkedHashSet<>(liveOut);
			liveIn.removeAll(blkDef.get(x));
			liveIn.addAll(blkUse.get(x));

			if (!liveIn.equals(blkLiveIn.get(x))) {
				blkLiveIn.replace(x, liveIn);
				x.pre.forEach(y -> {
					if (!visit.contains(y)) {
						queue.push(y);
						visit.add(y);
					}
				});
			}
		}
	}

	// connect edges
	// maintain movelist & worklistmoves
	void build() {
		currentFunc.blocks.forEach(b -> {
			var live = new LinkedHashSet<>(blkLiveOut.get(b));
			var iter = b.insts.descendingIterator();
			while(iter.hasNext()) {
				var inst = iter.next();
				var use = inst.getUseList();
				var def = inst.getDefList();
				// mv rd, zero cannot be merged
				if (inst instanceof Mv newInst && newInst.rs1 != Reg.zero) {
					use.forEach(live::remove);
					use.forEach(n -> moveList.get(n).add(newInst));
					def.forEach(n -> moveList.get(n).add(newInst));
					worklistMoves.add(newInst);
				}
				live.addAll(def);
				def.forEach(d -> live.forEach(l -> addEdge(d, l)));
				def.forEach(live::remove);
				live.addAll(use);
			}
		});
	}

	// initial -> spill/freeze/simplify worklist
	void makeWorklist() {
		var iter = initial.iterator();
		while (iter.hasNext()) {
			var n = iter.next();
			if (degree.get(n).get() >= K) {
				spillWorklist.add(n);
			} else if (moveRelated(n)) {
				freezeWorklist.add(n);
			} else {
				simplifyWorklist.add(n);
			}
			iter.remove();
		}
	}

	// here performance is critical
	LinkedHashSet<Reg> adjacent(Reg reg) {
		var res = new LinkedHashSet<>(adjList.get(reg));
		selectStack.forEach(res::remove);
		res.removeAll(coalescedNodes);
		// coalescedNodes.forEach(res::remove);
		return res;
	}

	// and here
	LinkedHashSet<Mv> nodeMoves(Reg reg) {
		var move = moveList.get(reg);
		var res = new LinkedHashSet<Mv>();
		for (var x : move) {
			if (activeMoves.contains(x) || worklistMoves.contains(x)) {
				res.add(x);
			}
		}
		// var res = new LinkedHashSet<>(activeMoves);
		// res.addAll(worklistMoves);
		// res.retainAll(moveList.get(reg));
		return res;
	}

	boolean moveRelated(Reg reg) {
		return !nodeMoves(reg).isEmpty();
	}

	void simplify() {
		var reg = simplifyWorklist.iterator().next();
		simplifyWorklist.remove(reg);
		selectStack.push(reg);
		adjacent(reg).forEach(x -> decrementDegree(x));
	}

	void decrementDegree(Reg reg) {
		var d = degree.get(reg).getAndDecrement();
		if (d == K) {
			var adj = adjacent(reg);
			adj.add(reg);
			enableMoves(adj);
			spillWorklist.remove(reg);
			if (moveRelated(reg)) {
				freezeWorklist.add(reg);
			} else {
				simplifyWorklist.add(reg);
			}
		}
	}

	void enableMoves(LinkedHashSet<Reg> nodes) {
		nodes.forEach(x -> nodeMoves(x).forEach(y -> {
			if (activeMoves.contains(y)) {
				activeMoves.remove(y);
				worklistMoves.add(y);
			}
		}));
	}

	void coalesce() {
		var m = worklistMoves.iterator().next();
		Reg x = getAlias(m.rd), y = getAlias(m.rs1);
		Reg u, v;
		if (isPrecolored(y)) { // (u, v): (not, not) or (pre, not) or (pre, pre)
			u = y;
			v = x;
		} else {
			u = x;
			v = y;
		}
		worklistMoves.remove(m);
		if (u == v) {
			coalescedMoves.add(m);
			addWorkList(u);
		} else if (isPrecolored(v) || isConnected(u, v)) { // both pre-colored or connected
			constrainedMoves.add(m);
			addWorkList(u);
			addWorkList(v);
		} else {
			boolean flag = false;
			if (isPrecolored(u)) {
				var adj = adjacent(v);
				for (var t : adj) {
					if (ok(t, u)) {
						flag = true;
						break;
					}
				}
			} else {
				var adj = adjacent(v);
				adj.addAll(adjacent(u));
				if (conservative(adj)) {
					flag = true;
				}
			}
			if (flag) {
				coalescedMoves.add(m);
				combine(u, v);
				addWorkList(u);
			} else {
				activeMoves.add(m);
			}
		}
	}

	void addWorkList(Reg reg) {
		if (!isPrecolored(reg) && !moveRelated(reg) && degree.get(reg).get() < K) {
			freezeWorklist.remove(reg);
			simplifyWorklist.add(reg);
		}
	}

	// george
	// a-t, r, a and r can be merged
	boolean ok(Reg t, Reg r) {
		return isPrecolored(t) || degree.get(t).get() < K || isConnected(t, r);
	}

	// briggs
	//
	boolean conservative(LinkedHashSet<Reg> nodes) {
		AtomicInteger k = new AtomicInteger();
		nodes.forEach(x -> {
			if (degree.get(x).get() >= K) k.incrementAndGet();
		});
		return k.get() < K;
	}

	Reg getAlias(Reg reg) {
		if (coalescedNodes.contains(reg)) {
			return getAlias(alias.get(reg));
		} else {
			return reg;
		}
	}

	void combine(Reg u, Reg v) {
		if (freezeWorklist.contains(v)) {
			freezeWorklist.remove(v);
		} else {
			spillWorklist.remove(v);
		}
		coalescedNodes.add(v);
		alias.put(v, u);
		moveList.get(u).addAll(moveList.get(v));
		enableMoves(new LinkedHashSet<>(List.of(v)));
		adjacent(v).forEach(t -> {
			addEdge(t, u);
			decrementDegree(t);
		});
		if (degree.get(u).get() >= K && freezeWorklist.contains(u)) {
			freezeWorklist.remove(u);
			spillWorklist.add(u);
		}
	}

	void freeze() {
		var u = freezeWorklist.iterator().next();
		freezeWorklist.remove(u);
		simplifyWorklist.add(u);
		freezeMoves(u);
	}

	void freezeMoves(Reg u) {
		nodeMoves(u).forEach(m -> {
			var x = getAlias(m.rd);
			var y = getAlias(m.rs1);
			var v = (y == getAlias(u) ? x : y);
			activeMoves.remove(m);
			frozenMoves.add(m);
			if (nodeMoves(v).isEmpty() && degree.get(v).get() < K) {
				freezeWorklist.remove(v);
				simplifyWorklist.add(v);
			}
		});
	}

	void selectSpill() {
		var m = spillWorklist.iterator().next();
		spillWorklist.remove(m);
		simplifyWorklist.add(m);
		freezeMoves(m);
	}

	// select stack -> colorednodes / spillnodes
	// coalescednodes -> color as alias
	void assignColors() {
		while (!selectStack.isEmpty()) {
			var n = selectStack.pop();
			var okColors = new ArrayList<>(Reg.colorReg);
			adjList.get(n).forEach(w -> {
				var w1 = getAlias(w);
				if (coloredNodes.contains(w1) || isPrecolored(w1)) {
					okColors.remove(w1.color);
				}
			});
			if (okColors.isEmpty()) {
				spilledNodes.add(n);
			} else {
				coloredNodes.add(n);
				n.color = okColors.iterator().next();
			}
		}
		coalescedNodes.forEach(n -> {
			n.color = getAlias(n).color;
		});
	}

	void rewriteProgram() {
		spilledNodes.forEach(v -> {
			stackDelta += 4;
			var imm = new Imm(stackDelta, false);
			currentFunc.blocks.forEach(b -> {
				var iterator = b.insts.listIterator();
				while (iterator.hasNext()) {
					var op = iterator.next();
					Reg reg = null;
					// one or more of rs1, rs2, rd may be equal to v
					// then allocate new reg
					if (op.rs1 == v || op.rs2 == v || op.rd == v) {
						reg = new VirtualReg();
					}
					// add load or store inst
					if (op.rs1 == v || op.rs2 == v) {
						// xxx rs1 ->
						// lw rs1' imm(sp)
						// xxx rs1'
						iterator.previous();
						iterator.add(new Lw(reg, Reg.sp, imm));
						iterator.next();
					}
					if (op.rd == v) {
						// xxx rd ->
						// xxx rd'
						// sw rd' imm(sp)
						iterator.add(new Sw(reg, Reg.sp, imm));
					}
					// modify rs1, rs2, rd; this step must be the last
					if (op.rs1 == v) op.rs1 = reg;
					if (op.rs2 == v) op.rs2 = reg;
					if (op.rd == v) op.rd = reg;
				}
			});
		});
//		spilledNodes.clear();
//		initial.clear();
//		initial.addAll(coloredNodes);
//		initial.addAll(coalescedNodes);
//		initial.addAll(newTemps);
//		coloredNodes.clear();
//		coalescedNodes.clear();
	}
}
