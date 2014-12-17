#!/usr/bin/env bash
set -e # Bail early
set -x # Very verbose

nasm -f elf64 -o helloworld.o helloworld.asm
ld -o helloworld helloworld.o

