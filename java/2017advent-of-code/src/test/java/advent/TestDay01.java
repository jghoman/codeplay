package advent;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static advent.Day01.compute;
public class TestDay01 {
  //  1122 produces a sum of 3 (1 + 2) because the first digit (1) matches the second digit and the third digit (2) matches the fourth digit.
  //  1111 produces 4 because each digit (all 1) matches the next.
  //  1234 produces 0 because no digit matches the next.
  //  91212129 produces 9 because the only digit that matches the next one is the last digit, 9.
  @Test
  public void test1122() {
    assertEquals(3, compute("1122"));
  }

  @Test
  public void test1111() {
    assertEquals(4, compute("1111"));
  }

  @Test
  public void test1234() {
    assertEquals(0, compute("1234"));
  }

  @Test
  public void test91212129() {
    assertEquals(9, compute("91212129"));
  }
}
