# The prime factors of 13195 are 5, 7, 13 and 29.

# What is the largest prime factor of the number 600851475143 ?

def divides_three(n)
  n % 3 == 0
end

def divides_five(n)
  n % 5 == 0
end

puts (1..1000).select{ |x| divides_three(x) || divides_five(x)}.sum()