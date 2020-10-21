const std = @import("std");
const assert = std.debug.assert;

test "anonymous list literal syntax" {
  var array: [4]u8 = .{11, 22, 33, 44};
  assert(array[0] == 11);
  assert(array[1] == 22);
  assert(array[2] == 33);
  assert(array[3] == 44);
}
