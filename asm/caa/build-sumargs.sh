#!/usr/bin/env bash
set -e # Bail early
set -x # Very verbose

F=sumargs
nasm -f elf64 -o $F.o $F.asm
ld -o $F $F.o

