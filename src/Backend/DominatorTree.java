package Backend;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import IR.Value.Global.BasicBlock;
import IR.Value.Global.Function;

public class DominatorTree {

    public Function currentFunction;

    public DominatorTree(Function func) {
        this.currentFunction = func;
    }

    public HashSet<BasicBlock> visited = new HashSet<>();
    public ArrayList<BasicBlock> rBlocks = new ArrayList<>();

    public void dfsBlock(BasicBlock block) {
        visited.add(block);
        block.nxt.forEach(x -> {
            if (!visited.contains(x)) dfsBlock(x);
        });
        rBlocks.add(0, block);
    }

    public HashMap<BasicBlock, Integer> dfn = new HashMap<>();
    public HashMap<BasicBlock, BasicBlock> iDom = new HashMap<>();
    public HashMap<BasicBlock, ArrayList<BasicBlock>> domSon = new HashMap<>();
    public HashMap<BasicBlock, ArrayList<BasicBlock>> domFr = new HashMap<>();

    public BasicBlock intersect(BasicBlock a, BasicBlock b) {
        if (a == null) return b;
        if (b == null) return a;
        while (a != b) {
            while (dfn.get(a) > dfn.get(b)) a = iDom.get(a);
            while (dfn.get(a) < dfn.get(b)) b = iDom.get(b);
        }
        return a;
    }

    public void domTree() {
        for (int i = 0; i < rBlocks.size(); i++) {
            dfn.put(rBlocks.get(i), i);
            iDom.put(rBlocks.get(i), null);
            domSon.put(rBlocks.get(i), new ArrayList<>());
        }
        iDom.replace(currentFunction.blocks.get(0), currentFunction.blocks.get(0));
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 1; i < rBlocks.size(); i++) {
                BasicBlock new_iDom = null;
                for (int i1 = 0; i1 < rBlocks.get(i).pre.size(); i1++) {
                    if (iDom.get(rBlocks.get(i).pre.get(i1)) != null)
                        new_iDom = intersect(new_iDom, rBlocks.get(i).pre.get(i1));
                }
                if (iDom.get(rBlocks.get(i)) != new_iDom) {
                    iDom.replace(rBlocks.get(i), new_iDom);
                    changed = true;
                }
            }
        }
        iDom.forEach((x, f) -> {
            if (f != null && x != f) domSon.get(f).add(x);
        });
    }

    public void domFrontier() {
        rBlocks.forEach(x -> domFr.put(x, new ArrayList<>()));
        rBlocks.forEach(x -> {
            if (x.pre.size() >= 2) {
                x.pre.forEach(p -> {
                    BasicBlock r = p;
                    while (r != iDom.get(x)) {
                        domFr.get(r).add(x);
                        r = iDom.get(r);
                    }
                });
            }
        });
    }

    public ArrayList<BasicBlock> rNodes = new ArrayList<>();
    public HashMap<BasicBlock, HashSet<BasicBlock>> domSubTree = new HashMap<>();

    public void dfsTree(BasicBlock x) {
        HashSet<BasicBlock> sub = new HashSet<>();
        domSon.get(x).forEach(a -> {
            dfsTree(a);
            sub.add(a);
            sub.addAll(domSubTree.get(a));
        });
        rNodes.add(x);
        domSubTree.put(x, sub);
    }

    public void work() {
        if (currentFunction.blocks.size() == 0) return;
        dfsBlock(currentFunction.blocks.get(0));
        domTree();
        domFrontier();
        dfsTree(currentFunction.blocks.get(0));
    }
}
