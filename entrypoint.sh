#!/bin/bash
ANSWERS=0
if [ "$1" == "--answers" ]; then
  ANSWERS=1
  shift
fi
if [ $# -ge 1 ]; then
  if [ ! -d "/app/$1" ]; then
    echo "Invalid Day"
    exit 1
  fi
fi
if [ $# -eq 1 ]; then
  if [ $ANSWERS -eq 1 ]; then
    cat $1/answers.txt
  else
    time php $1/run.php
  fi
else
  echo "You must specify the day"
  exit 1
fi
