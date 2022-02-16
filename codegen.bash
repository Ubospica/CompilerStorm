#!/usr/bin/bash
set -e
#cd ..

#java -cp ./lib/antlr-4.9.2-complete.jar:./bin CompilerMain.Main -codegen
java -cp /ulib/java/antlr-4.9.1-complete.jar:./bin CompilerMain.Main -codegen
#clang a.ll ./src/Builtin/BuiltinFuncC.ll -Wall -Wextra
#./a.out < tmp/a.in > tmp/a.output
#diff tmp/a.output tmp/a.ans -Z
#echo $?