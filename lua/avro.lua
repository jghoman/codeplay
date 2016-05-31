
local inp = assert(io.open("./doctors.avro", "rb"))

local data = inp:read(5 )

for i = 1, #data do
  c = string.sub(data, i, i)
  print(c .. " - > " .. string.byte(c))
end
print("data = [" .. data .. "]")
