import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day8Test {
    @Test
    fun testCountEasyNumbers() {
        val nofEasyNumbers = countOnlyEasyNumbers("day8_test.txt")
        assertEquals(26, nofEasyNumbers)
    }

    @Test
    fun testSumNumbers() {
        val sumOfNumbers = sumNumbers("day8_test.txt")
        assertEquals(61229, sumOfNumbers)
    }
}