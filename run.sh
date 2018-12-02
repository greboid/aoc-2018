#!/bin/bash
IMAGE=greboid/aoc-2018-03
if [ "$(docker images -q $IMAGE)" == "" ]
then
    docker build . -t $IMAGE
fi
docker run --rm -it -v $(pwd):/app $IMAGE $@
