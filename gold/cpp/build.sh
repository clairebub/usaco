#!/bin/sh
if [ "$1" == "" ]; 
then
  echo "error: need to pass in the source file name"
  exit 1
fi
g++ -O2 -lm -std=c++0x $1
