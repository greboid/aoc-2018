#!/bin/sh
if [ $# -ne 2 ]; then
    echo "Must include day and part to run"
    exit 1
fi
#docker run --rm -v $(pwd):/app composer/composer install
docker run --rm -it -v $PWD:/app -w /app php:7.3-rc-cli php $1/$2.php
