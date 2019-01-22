#!/usr/bin/env bash
set -e # Bail early
#set -x # Very verbose

docker build . -t tf2
