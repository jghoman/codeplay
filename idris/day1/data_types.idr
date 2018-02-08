data DumbNumber = Naught | One | Two | Three

data Natural = Zero | After Natural

data MyList a = Blank | (::) a (MyList a)

-- These are equivalent due to the parens around ::
-- (::) "Watson" Blank
-- "Watson" :: Blank

data Perhaps a = Nothing | Just a

first : MyList a -> Perhaps a
first Blank = Nothing
first (x :: xs) = Just x

firstString : Perhaps String
firstString = first ("Elementary" :: Blank)

-- 'the' is a function that takes a type and a value
-- of that type and returns itself
-- (http://www.parsonsmatt.org/2016/02/18/dependent-hardware-i.html)
secondString : Perhaps String
secondString = first (the (MyList String) Blank)