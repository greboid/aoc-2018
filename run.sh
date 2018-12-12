if [ $# -ge 1 ]; then
  if [ ! -d "$1" ]; then
    echo "Invalid Day"
    exit 1
  fi
fi
if [ -f $1/src/main.rs ]; then
  IMAGE=greboid/aoc-2018-rust-01
  FILE=Dockerfile-rust
else
  IMAGE=greboid/aoc-2018-15
  FILE=Dockerfile
fi
if [ "$(docker images -q $IMAGE)" == "" ]; then
  docker build -f$FILE . -t $IMAGE
fi
docker run --rm -it -v $(pwd):/app $IMAGE $@
