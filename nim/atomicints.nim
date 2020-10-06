import os
import atomics

proc say_hi(i:Atomic[int]) =
  i += 1
  echo "hi ",i

proc main = 
  var
    count : Atomic[int]
    zthreads : array[0..20, Thread[Atomic[int]]]

  for i in 0..20:
    createThread[Atomic[int]](zthreads[i], say_hi, count)
  zthreads.joinThreads

main()
