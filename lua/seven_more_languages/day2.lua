book = {
    title = "Grail Diary",
    author = "Henry Jones",
    pages = 100
}

greek_numbers = {
  ena = "one",
  dyo = "two",
  tria = "three"
}

function table_to_string(t)
  local result = {}

  for k, v in pairs(t) do
    result[#result + 1] = k .. ": " .. v
  end

  return table.concat(result, "\n")
end

local _private = {}

function strict_read(table, key)
  if _private[key] then
    return _private[key]
  else
    error("Invalid key: " .. key)
  end
end

function strict_write(table, key, value)
  if _private[key] then
    error("Duplicate key " .. key)
  else
    _private[key] = value
  end
end

local mt = {
  __index = strict_read,
  __newindex = strict_write
}

treasure = {}
setmetatable(treasure, mt)

-- Villain stuff

Villain = { health = 100 }

function Villain:new(name)
  local obj = {
    name   = name,
    health = self.health,
  }
  
  setmetatable(obj, self)
  self.__index = self
  return obj
end

function Villain:take_hit()
  self.health = self.health - 10
end

dietrich = Villain:new("Dietrich")
dietrich:take_hit()
