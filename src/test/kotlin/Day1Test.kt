import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day1Test {
    @Test
    fun testDay1() {
        val result = runDay1("day1_test.txt")
        assertEquals(7, result)
    }

    @Test
    fun testDay1Part2() {
        val result = runDay1PartTwo("day1_test.txt")
        assertEquals(5, result)
    }
}

