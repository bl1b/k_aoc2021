import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day3Test {
    @Test
    fun testDay3() {
        val result = determineGammeAndEpsilon("day3_test.txt")
        assertEquals(198, result)
    }

    @Test
    fun testOxygenAndCO2() {
        val result = determineOxygenAndCO2("day3_test.txt")
        assertEquals(230, result)
    }
}