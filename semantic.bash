set -e

#cat | java -cp /ulib/java/antlr-4.9.1-complete.jar:./bin Main -semantic
java -cp ./lib/antlr-4.9.2-complete.jar:./bin CompilerMain.Main -semantic