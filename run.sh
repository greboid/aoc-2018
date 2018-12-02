#!/bin/bash
IMAGE=greboid/aoc-2018-01
docker image inspect $IMAGE >/dev/null 2>&1
if [ "$(docker images -q $IMAGE)" == "" ]
then
    docker build . -t $IMAGE
fi
docker run --rm -it -v $(pwd):/app $IMAGE $@
