section .data
  msg db "hello, world!"

section .text
  global _start

_start:
  mov   rax, 1  ; temporary register, used by syscall
  mov   rdi, 1  ; used to pass first argument to functions - is fd
  mov   rsi, msg ; used to pass second argument to functions - is point to char buffer
  mov   rdx, 13 ; used to pass third arguments to functions - is lenght of char buffer
  syscall
  mov   rax, 60
  mov   rdi, 0
  syscall

