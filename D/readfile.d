import std.stdio;

// Adapted from http://stackoverflow.com/questions/3727613/d-file-i-o-functions
void readTest() {
  auto f = File("../wordsEn.txt");
  scope(exit) f.close();

  foreach(str; f.byLine)
    writeln("::", str);
}

void main() {
  readTest();
}
