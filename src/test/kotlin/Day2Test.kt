import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day2Test {
    @Test
    fun testDay2() {
        val result = runDay2("day2_test.txt", Submarine())
        assertEquals(150, result)
    }

    @Test
    fun testDay2PartTwo() {
        val result = runDay2("day2_test.txt", AimedSubmarine())
        assertEquals(900, result)
    }
}