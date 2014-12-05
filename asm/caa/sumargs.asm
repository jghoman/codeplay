section .data
  SYS_WRITE equ 1
  SYS_OUT   equ 0
  SYS_IN    equ 1
  SYS_EXIT  equ 60
  EXIT_CODE equ 0

  NEW_LINE  db 0xA
  WRONG_ARGC db "Must be two command line arguments", 0xA

section .text
  global _start

_start:
  pop rcx          ; Get argument count from stack
  cmp rcx, 3       ; we got three args, right?
  jne argcError    ; if not, complain and bail

  add rsp, 8       ; skip the first byte on the stack
  pop rsi          ; grab first number to add
  call str_to_int  ; parse

  mov r10, rax     ; move result of parsing (rax) to r10
  pop rsi          ; get next argument from stack
  call str_to_int  ; parse
  mov r11, rax     ; move result back

  add r10, r11     ; add values, store in r10

  mov rax, r10     ; move sum to register
  xor r12, r12     ; zero out r12, for some reason...
  jmp int_to_str   ; print out value

argcError:
  mov rax, SYS_WRITE       ; Going to sys_write syscall
  mov rdi, SYS_OUT  ; Write to stdout
  mov rsi, WRONG_ARGC  ; Text: wrong arguments
  mov rdx, 35       ; Length of text arguments
  syscall
  jmp exit;

str_to_int:
  xor rax, rax    ; zero out rax
  mov rcx, 10     ; base 10 multiplier
next:
  cmp [rsi], byte 0 ; Check if we've reached the end of the string yet
  je return_str     ; if so, we're done, return value
  mov bl, [rsi]     ; else, copy value to byte register
  sub bl, 48        ; Subtract 48 to get int value
  mul rcx           ; Multiply the running total by 10 to make room for this new value
  add rax, rbx      ; add this component to running total
  inc rsi           ; Increment byte pointer to get next value
  jmp next          ; loop

return_str:
  ret

int_to_str:
  mov rdx, 0       ; zero out rdx
  mov rbx, 10      ; divisor
  div rbx          ; divide by 10
  add rdx, 48      ; Boot up to ascii tavlue
  add rdx, 0x0     ; Terminate string
  push rdx         ; push string result to stack
  inc r12          ; keep track of string length
  cmp rax, 0x0     ; Check if we're done
  jne int_to_str   ; if not, keep working
  jmp print

print:
  mov rax, 1      ; 
  mul r12
  mov r12, 8
  mul r12
  mov rdx, rax

  mov rax, SYS_WRITE
  mov rdi, SYS_OUT
  mov rsi, rsp
  syscall
  jmp exit

exit:
   mov rax, SYS_EXIT
   mov rdi, EXIT_CODE
   syscall
