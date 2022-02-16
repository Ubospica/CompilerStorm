set -e

mkdir -p bin
#find ./src -name *.java | javac -d bin -cp ./lib/antlr-4.9.2-complete.jar @/dev/stdin
find ./src -name *.java | javac -d bin -cp /ulib/java/antlr-4.9.1-complete.jar @/dev/stdin
