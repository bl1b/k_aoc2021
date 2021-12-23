import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day7Test {
    @Test
    fun testMinimumFuel() {
        val minimumFuel = calculateMinimumFuelConsumption("day7_test.txt", ::linearConsumptionFunc)
        assertEquals(37, minimumFuel)
    }

    @Test
    fun minimumFuelWithAlternatingConsumptionRate() {
        val minimumFuel = calculateMinimumFuelConsumption("day7_test.txt", ::exponentialConsumptionFunc)
        assertEquals(168, minimumFuel)
    }
}