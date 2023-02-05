package Backend;

import IR.Pass;
import IR.Type.FuncType;
import IR.Type.PointerType;
import IR.Type.StructType;
import IR.Type.Type;
import IR.Value.Constant.IntConstant;
import IR.Value.Constant.NullConstant;
import IR.Value.Constant.StrConstant;
import IR.Value.Constant.ZeroInitConstant;
import IR.Value.Global.BasicBlock;
import IR.Value.Global.Function;
import IR.Value.Global.Module;
import IR.Value.Global.Variable;
import IR.Value.Inst.*;
import IR.Value.Use;
import IR.Value.Value;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.antlr.v4.runtime.misc.Pair;



public class Mem2Reg implements Pass {
    private DominatorTree domTree;

    LinkedList<Inst> varList = new LinkedList<>();
    LinkedList<Value> renameStack = new LinkedList<>();

	public void visit(Module it) {
        it.gFunc.forEach((name, func) -> {
            if (func.blocks.size() == 0) {
                return;
            }
            domTree = new DominatorTree(func);
            domTree.work();
            getVarList(func);
            insertPhi(func);
            // rename var
            varList.forEach(v -> {
                renameStack.clear();
                renameStack.push(v);
                if (((AllocaInst)v).isParam) {
                    renameStack.push(v.useList.get(0).user.getUse(0));
                }
                renameVar(v, func.blocks.get(0));
            });
            // simplifyPhi(func);
        });
    }

    // get all alloca insts and removes them from blocks
    public void getVarList(Function func) {
        varList.clear();
        func.blocks.forEach(b -> {
            var iter = b.insts.listIterator();
            while (iter.hasNext()) {
                var inst = iter.next();
                if (inst instanceof AllocaInst) {
                    varList.add(inst);
                    iter.remove();
                }
            }
        });
    }

    public void insertPhi(Function func) {
        varList.forEach(inst -> {
            var visitedBlocks = new HashSet<BasicBlock>();
            var useList = new LinkedList<>(inst.useList);
            while (!useList.isEmpty()) {
                var user = useList.pop().user;
                // consider assignments to inst
                if (user instanceof StoreInst || user instanceof PhiInst) {
                    domTree.domFr.get(((Inst)user).inBlock).forEach(b1 -> {
                        if (!visitedBlocks.contains(b1)) {
                            var phi = new PhiInst(true, inst);
                            b1.insts.addFirst(phi);
                            phi.inBlock = b1;
                            useList.push(new Use(phi, inst));
                            visitedBlocks.add(b1);
                        }
                    });
                }
            }
        });
    }

    public void renameVar(Inst var, BasicBlock b) {
        var ve = renameStack.peek();

        var iter = b.insts.listIterator();
        while (iter.hasNext()) {
            var inst = iter.next();
            if (inst instanceof LoadInst && inst.getUse(0) == var) {
                iter.remove();
            } else if (inst instanceof StoreInst && inst.getUse(1) == var) {
                renameStack.push(inst.getUse(0));
                iter.remove();
            } else if (inst instanceof PhiInst phiInst && phiInst.isDomPhi) {
                if (phiInst.target == var) {
                    renameStack.push(inst);
                }
            } else {
                for (var i : inst.operandList) {
                    if (i.val instanceof LoadInst loadInst && loadInst.getUse(0) == var) {
                        i.val = renameStack.peek();
                        renameStack.peek().useList.add(i);
                    }
                }
            }
        }

        b.nxt.forEach(nxt -> nxt.insts.forEach(i -> {
            if (i instanceof PhiInst phiInst&& phiInst.target == var) {
                // if (renameStack.size() > 1)
                phiInst.add(renameStack.peek(), b);
                if (phiInst.type == Type.VOID) {
                    phiInst.type = renameStack.peek().type;
                }
                // else phiInst.add(b, x.type.getInit());
            }
        }));
        domTree.domSon.get(b).forEach(s -> renameVar(var, s));
        while (renameStack.peek() != ve) renameStack.pop();
    }


    public void simplifyPhi(Function func) {
        // size=1
        func.blocks.forEach(t -> {
            for (int i = 0; i < t.insts.size(); i++) {
                Inst x = t.insts.get(i);
                if (x instanceof PhiInst phiInst && phiInst.args.size() == 1) {
                    var value = phiInst.args.get(0).a;
                    phiInst.useList.forEach(u -> u.user.operandList.forEach(u1 -> {
                        if (u1.val == x) u1.val = value;
                    }));
                }
            }
        });
        // unused phi
        AtomicBoolean cond = new AtomicBoolean(true);
        while (cond.get()) {
            cond.set(false);
            func.blocks.forEach(b -> {
                var iter = b.insts.listIterator();
                while (iter.hasNext()) {
                    var inst = iter.next();
                    if (inst.useList.isEmpty()) {
                        iter.remove();
                        inst.operandList.forEach(u -> {
                            var iter1 = u.val.useList.listIterator();
                            while (iter1.hasNext()) {
                                var u1 = iter1.next();
                                if (u1.user == inst) {
                                    iter1.remove();
                                }
                            }
                        });
                    }
                }
            });
        }
    }

}
