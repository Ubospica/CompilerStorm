## CompilerStorm

*CompilerStorm* is a toy compiler of [Mx*](https://github.com/ACMClassCourses/Compiler-Design-Implementation/blob/master/README.md), a C++-like language designed for teaching by TAs from the ACM Class, targeting for [RISC-V 32-bit, Integer Extended](https://riscv.org/technical/specifications/). The CompilerStorm project is an assignment of the compiler course in the ACM Class.

The grammar of Mx* can be found in `doc/README.md`.

### Bash Reference
- Generate parser
```
java -Xmx500M -cp "./antlr-4.10.1-complete.jar:$CLASSPATH" org.antlr.v4.Tool  src/Parser/Mx.g4 -o . -listener -visitor -package Parser
```
- Generate llvm ir
```
clang -S -emit-llvm -Xclang -disable-O0-optnone a.cpp -o a.llvm.ll
opt -mem2reg a.llvm.ll -o a.llvm.bc // ir to ssa
llvm-dis a.llvm.bc -o a.llvm.opt.ll // disassemble ir
```
- Flame Graph
    - <https://blog.codecentric.de/jvm-fire-using-flame-graphs-analyse-performance>

```sh
# run with agent
java-agentpath:/home/ubospica/dev/java/honest-profiler/liblagent.so=interval=7,logPath=/tmp/tmp/log.hpl -cp xxx
# convert hpl to folded
# install openjfx first
java --module-path /usr/share/openjfx/lib --add-modules=javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web -cp ~/dev/java/honest-profiler/honest-profiler.jar com.insightfullogic.honest_profiler.ports.console.FlameGraphDumperApplication log.hpl log.folded
# convert folded to svg
~/dev/linux/FlameGraph/flamegraph.pl /tmp/tmp/log.folded > /tmp/tmp/flamegraph-java.svg
```
- Others
```
# cpp to ll
clang -emit-llvm -S a.cpp -fno-discard-value-names -O0
# ll to assembly
clang a.ll -Wall -Wextra -S
# ll to a.out
clang a.ll -Wall -Wextra
# run and print output
./a.out; echo $?
clang a.ll -Wall -Wextra; ./a.out; echo $?

# Codegen

# cpp to riscv llvm ir to riscv assembly
clang -emit-llvm -S a.cpp -o a.llvm.ll --target=riscv32 -O0
llc a.llvm.ll -o a.s -march=riscv32 --mattr=+m -O0

# compile & run llvm ir
clang a.ll ../src/Builtin/BuiltinFuncC.ll -Wall -Wextra && ./a.out

# cpp to riscv assembly
clang -S a.cpp -o a.s --target=riscv32 -O0

# ravel debug
ravel a.alloc.s ../src/Builtin/BuiltinFuncC.s --print-instructions
ravel a.alloc.s ../src/Builtin/BuiltinFuncC.s --input-file=test.in --output-file=test.out
ravel a.alloc.s ../src/Builtin/BuiltinFuncC.s --input-file=test.in --output-file=test.out --print-instructions 2> tmp


# compile and run java
javac ../src/**/*.java -d ../bin -cp ../lib/antlr-4.9.2-complete.jar  -Xlint:unchecked
java -cp ../lib/antlr-4.9.2-complete.jar:../bin/ CompilerMain.Main

# builtin c to s
clang -emit-llvm -S BuiltinFuncC.c -o BuiltinFuncC.ll
# target triple = "riscv32"
# llc BuiltinFuncC.ll -o BuiltinFuncC.s -march=riscv32 --mattr=+m -O0
clang -S BuiltinFuncC.ll -o BuiltinFuncC2.s --target=riscv32 -march=rv32ima -O2

```

### Development Progress
The project starts from `10/10/2021`. Recent updates about the project are shown below.

- `10/15/2021`: Draft of g4 finished.

### Reading Materials

1. LLVM IR
	- [LLVM笔记(16) - IR基础详解(一) underlying class](https://www.cnblogs.com/Five100Miles/p/14083814.html)
	- [LLVM笔记(18) - IR基础详解(二) Instruction](https://www.cnblogs.com/Five100Miles/p/14100555.html)

### Java LangRef
1. Use ArrayList instead of Vector: faster, not thread safe
2. equals实现: 见`IR.Type.Type.equals` & `IR.Type.Type.IntType.equals`
3. 调用本类构造函数: `this(xxx);`
4. switch:
    ```
    var irTypeBase = switch (it.type) {
        case VOID -> Type.VOID;
        case INT -> {
            yield Type.INT32;
        }
        case STRING -> new PointerType(IntType.INT8);
        default -> null;
    };
    ```
    ```
    switch (left.type) {
        case ARRAY, CLASS -> {
            return true;
        }
        default -> {
            return false;
        }
    }
    ```

5. java flamegraph
    ```
    # in launch.json
    "vmArgs": "-agentpath:/home/ubospica/dev/java/honest-profiler/liblagent.so=interval=7,logPath=/tmp/tmp/log.hpl"
    # in bash
    java --module-path /usr/share/openjfx/lib --add-modules=javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web -cp ~/dev/java/honest-profiler/honest-profiler.jar com.insightfullogic.honest_profiler.ports.console.FlameGraphDumperApplication log.hpl log.folded
    ~/dev/linux/FlameGraph/flamegraph.pl /tmp/tmp/log.folded > /tmp/tmp/flamegraph-java.svg
    ```

### Q&A

### TODO List
1. codegen
   - [x] 18
   - [x] 61
   - [x] 63
   - [x] 64
