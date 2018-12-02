#!/bin/sh
if [ $# -eq 1 ]; then
    php $1/a.php
    php $1/b.php
    exit 1
elif [ $# -eq 2 ]; then
    php $1/$2.php
    exit 1
else
  echo "Need some parameters."
fi

exec "$@"
