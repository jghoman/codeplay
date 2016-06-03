Person = {}

function Person:new(first, last)
  local obj = {
    first = first,
    last = last
  }

  setmetatable(obj, self)
  self.__index = self
  return obj
end

function Person:say_hi()
  print("Hi, I'm " .. self.first .. " " .. self.last)
end

bob = Person:new("Bob", "Thompson")

bob:say_hi()

Student = Person:new()

function Student:new(first, last, gpa)
  local obj = Person:new(first, last)
  obj.gpa = gpa
  setmetatable(obj, self)
  self.__index = self
  return obj
end

function Student:say_gpa()
  print("I am " .. self.first .. " and my gpa is " .. self.gpa)
end

alice = Student:new("Alice", "Jones", 3.82)
alice:say_hi()
alice:say_gpa()

