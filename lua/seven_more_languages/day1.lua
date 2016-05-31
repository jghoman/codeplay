function ends_in_3(num)
  local asString = tostring(num)
  local last = string.sub(asString, string.len(asString))
  return last == '3'
end

print(ends_in_3(23243))
print(ends_in_3(222))

function is_prime(num)
  local half = num / 2

  for i = 2, half do
    if  num % i == 0 then
      return false
    end
  end

  return true
end

for i = 1, 100 do
  if ends_in_3(i) and is_prime(i) then
    print(i .. " both ends in 3 and is prime")
  end
end

function add(previous, next) 
  return previous + next
end

function reduce(max, init, f)
  local intermediate = f(init, 1)

  for i = 2, max do
    intermediate = f(intermediate, i)
  end
  return intermediate
end

print("reduce from 0 to 5 = " .. reduce(5, 0, add))

function factorial(n)
  return reduce(n, 1, function(a,b) return a * b end)
end

print("factorial 9 = " .. factorial(9))
