set -e
#cd ..
mkdir -p bin
find ./src -name *.java | javac -d bin -cp  ./antlr-4.10.1-complete.jar @/dev/stdin
#find ./src -name *.java | javac -d bin -cp /ulib/java/antlr-4.9.1-complete.jar @/dev/stdin
