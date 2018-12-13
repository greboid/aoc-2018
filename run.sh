RUSTIMAGE=greboid/aoc-2018-rust-02
JAVAIMAGE=greboid/aoc-2018-15

if [ "$(docker images -q $RUSTIMAGE)" == "" ]; then
  docker build -fDockerfile-rust . -t $RUSTIMAGE > /dev/null 2>&1
fi
if [ "$(docker images -q $JAVAIMAGE)" == "" ]; then
  docker build -fDockerfile . -t $JAVAIMAGE > /dev/null 2>&1
fi

if [ $# -ge 1 ]; then
  if [ ! -d "$1" ]; then
    echo "Invalid Day"
    exit 1
  fi
fi
if [ -f $1/src/main.rs ]; then
  IMAGE=$RUSTIMAGE
else
  IMAGE=$JAVAIMAGE
fi
docker run --rm -it -v $(pwd):/app $IMAGE $@
