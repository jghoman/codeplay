echo "Hello, world!"

proc is_palindrome(i:int): bool =
  let asString = $(i)

  let length = len(asString) - 1
  let toCheck = length div 2
  
  var count = 0
  while count <= (toCheck):
    if asString[count] != asString[length - count]:
        return false
    inc(count)

  return true

var max = 0
for i in countup(100, 999):
    for j in countup(100, 999):
        let product = i * j
        if is_palindrome(product) and product > max:
            max = product

echo "Largest product = " & $(max)

#echo "is palindrome = " & $(is_palindrome(1234321))