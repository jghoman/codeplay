#!/usr/bin/env bash
set -e # Bail early
#set -x # Very verbose

docker run -it -p 8888:8888 -v $PWD:/home/jovyan tf2
