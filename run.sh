#!/bin/sh
if [ $# -eq 1 ]; then
    docker run --rm -it -v $PWD:/app -w /app php:7.3-rc-cli php $1/a.php
    docker run --rm -it -v $PWD:/app -w /app php:7.3-rc-cli php $1/b.php
    exit 1
fi
if [ $# -eq 2 ]; then
    docker run --rm -it -v $PWD:/app -w /app php:7.3-rc-cli php $1/$2.php
    exit 1
fi
