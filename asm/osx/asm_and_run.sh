#! /bin/bash

rm exit.o exit

as exit.s -o exit.o
ld exit.o -e _main -o exit # -e = specify the entry point of the executable
./exit

echo $?
