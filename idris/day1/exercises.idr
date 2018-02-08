
-- easy
-- Find the numbers in a list that are greater than a given number.
greaterThan : List Integer -> Integer -> List Integer
greaterThan  xs n = filter ( > n) xs 

-- Find every other member of a list, starting with the first member.
everyOther : List a -> List a
everyOther [] = []
everyOther (x :: []) = [x]
everyOther (x :: y :: xs) = x :: everyOther xs

-- Build a data type representing a playing card from a standard poker deck.
data Suite = Hearts | Spades | Clubs | Diamonds
data Rank  = Ace | Two | Three | Four | Five | Six | Seven | Eight | Nine | Ten | Jack | Queen | King
data Card = C Rank Suite

MyCard : Card
MyCard = C Ace Spades