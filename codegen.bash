#!/usr/bin/bash

java -cp ./lib/antlr-4.9.2-complete.jar:./out/production/CompilerStorm CompilerMain.Main -codegen
clang a.ll ./src/Builtin/BuiltinFuncC.ll -Wall -Wextra
./a.out < tmp/a.in > tmp/a.output
diff tmp/a.output tmp/a.ans -Z
echo $?