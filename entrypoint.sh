#!/bin/sh
if [ $# -ge 1 ]; then
  if [ ! -d "/app/$1" ]; then
    echo "Invalid Day"
    exit 1
  fi
fi
if [ $# -eq 1 ]; then
  time sh -c "php $1/a.php; php $1/b.php"
  exit 1
elif [ $# -eq 2 ]; then
  if [ -f "/app/$1/$2.php" ]; then
    time php $1/$2.php
  else
    echo "Invalid part"
  fi
  exit 1
else
  echo "You must specify at least the day"
fi
