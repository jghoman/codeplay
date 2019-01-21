#!/usr/bin/env bash
set -e # Bail early
#set -x # Very verbose


docker run -it --rm -v $PWD:/tmp -w /tmp tensorflow/tensorflow python ./script.py
