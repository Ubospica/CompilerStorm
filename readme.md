## CompilerStorm

*CompilerStorm* is a toy compiler of [Mx*](https://github.com/ACMClassCourses/Compiler-Design-Implementation/blob/master/README.md), a C++-like language designed for teaching by TAs from the ACM Class, targeting for [RISC-V 32-bit, Integer Extended](https://riscv.org/technical/specifications/). The CompilerStorm project is an assignment of the compiler course in the ACM Class.

The grammar of Mx* can be found in `doc/README.md`.



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

### Q&A
1. 