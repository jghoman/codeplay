File := Object clone

File init := method(patha,
  ("wtf " .. patha .. "\n") print
  #if(patha isNil, Exception raise("must specify path!"))
  self path := patha
  return self)

File printPath := method(
  ("my path is " .. path) print)

"hmmmmm\n" print
file1 := File clone("/foo/bar")

file1 printPath

#file path := "/foo/bar"
#file perm := (8**2) * 7 + (8**1) * 7 + (8**0) * 7
#file owner := "bob"
#file group := "accounting"

#file print

#file2 := file clone

#file owner = "sarah"

#"------" print
#file print
#"-----" print

#file2 print


