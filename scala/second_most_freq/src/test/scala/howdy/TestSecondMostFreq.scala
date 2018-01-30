import org.junit.Test
import org.junit.Assert._

class TestSecondMostFreq  {
    
    @Test
    def canAdd() {
        assertEquals(42, 2+2)
    }

    @Test
    def basicTest() {
        val input = Array('a', 'c', 'c', 'd', 'b', 'b', 'c', 'd', 'd', 'd', 'e')
        val expected = Seq('c')
        assertEquals(SecondMostFreq.secondMostFreq(input), expected);
    }
}