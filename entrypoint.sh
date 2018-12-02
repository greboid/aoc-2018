#!/bin/bash
if [ $# -ge 1 ]; then
  if [ ! -d "/app/$1" ]; then
    echo "Invalid Day"
    exit 1
  fi
fi
if [ $# -eq 1 ]; then
  time php $1/run.php
else
  echo "You must specify the day"
  exit 1
fi
