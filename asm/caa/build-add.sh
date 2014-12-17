#!/usr/bin/env bash
set -e # Bail early
set -x # Very verbose

F=add
nasm -f elf64 -o $F.o $F.asm
ld -o $F $F.o

