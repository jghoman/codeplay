data Color = Back | White
data Piece = Pawn | Knight | Bishop | Rook | Queen | King
data ChessPiece = CP Color Piece
piece = CP Black Queen

color = case piece of \
  CP White _ -> White \
  CP Black _ -> Black
    
data List a = Empty | Cons a (List a)

blackQueen = {color=Black, piece=Queen}
whiteQueen = {blackQueen | color <- white }

add x y = + y
double x = x * 2
anonymousInc = \x -> x + 1

5 |> anonymousInc |> double