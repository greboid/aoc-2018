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
    if [ -f $1/build.gradle ]; then
      cd $1
      if [ -w $1 ]; then
        gradle --no-daemon jar -q > /dev/null 2>&1
      else
        gradle --project-cache-dir=/tmp/.gradle -g /tmp/.gradle --no-daemon jar -q > /dev/null 2>&1
      fi
      time java -jar /tmp/build/libs/app.jar
    else
      time php $1/run.php
    fi
  fi
else
  echo "You must specify the day"
  exit 1
fi
