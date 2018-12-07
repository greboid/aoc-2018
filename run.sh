#!/bin/bash
IMAGE=greboid/aoc-2018-11
if [ "$(docker images -q $IMAGE)" == "" ]; then
    docker build . -t $IMAGE
fi
docker run --rm -v $(pwd):/app $IMAGE $@
