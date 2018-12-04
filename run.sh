#!/bin/bash
IMAGE=greboid/aoc-2018-06
if [ "$(docker images -q $IMAGE)" == "" ]; then
    docker build . -t $IMAGE
fi
docker run --rm -v $(pwd):/app $IMAGE $@
