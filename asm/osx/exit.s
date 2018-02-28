# Simple exit program

# OSX requires a writable data segment now...
.section __DATA,__data
str:
    .asciz "OK"

.section __TEXT,__text
.globl _main
_main:
    movl $0x2000001, %eax # system call $1 with $0x200000 offset
    movl $0, %ebx         # set the exit code to be $0
    syscall
