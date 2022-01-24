set -e
mkdir -p bin

if [[ -z $WSL_DISTRO_NAME ]]; then
  find ./src -name *.java | javac -d bin -cp ./lib/antlr-4.9.2-complete.jar @/dev/stdin
else
  find ./src -name *.java | javac -d bin -cp /ulib/java/antlr-4.9.1-complete.jar @/dev/stdin
fi
