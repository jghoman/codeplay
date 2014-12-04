section .data
  noteq:  db "They weren't equal!"
  wereeq: db "They were equal!"

section .text

  global _start

_start:
  mov rax, 1
  call incRax
  cmp rax, 2
  jne .notequal
  mov rax, 1
  mov rdi, 1
  mov rsi, wereeq
  mov rdx, 16
  syscall
  jmp .exit

.notequal:
  mov rax, 1
  mov rdi, 1
  mov rsi, noteq
  mov rdx, 19
  syscall
  jmp .exit

.exit:
  mov rax, 60
  mov rdi, 0
  syscall

incRax:
  inc rax
  ret
