module Proof

data Natural = Zero | Suc Natural

plus : Natural -> Natural -> Natural
plus Zero x1 = x1
plus (Suc x) x1 = Suc (plus x x1)