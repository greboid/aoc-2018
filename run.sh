#!/bin/bash
IMAGE=greboid/aoc-2018
docker image inspect $IMAGE >/dev/null 2>&1
if [ $? -ne 0 ]
then
    echo "One time setup: building docker image..."
    docker build . -t $IMAGE
fi
docker run --rm -it -v $(pwd):/app $IMAGE $@
