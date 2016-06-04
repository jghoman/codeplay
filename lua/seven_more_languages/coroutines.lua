function fibonacci()
  local m = 1
  local n = 1

  while true do
    coroutine.yield(m)
    m, n = n, m + n
  end
end

generator = coroutine.create(fibonacci)
for i = 1, 10 do
  _, value = coroutine.resume(generator)
  print(value)
end

function onetwothree()
  for i = 1, 3 do
    coroutine.yield(i)
  end

  return "done"
end

generator = coroutine.create(onetwothree)
for i = 1, 10 do
  local result, value = coroutine.resume(generator)
  print("result = " .. tostring(result) .. ", value = " .. tostring(value))
end
