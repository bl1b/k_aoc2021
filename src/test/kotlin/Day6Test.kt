import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.test.assertEquals

internal class Day6Test {
    @Test
    fun testSpawnLanternFish() {
        val result = spawnLanternfish("day6_test.txt", 80)
        assertEquals(BigInteger.valueOf(5934), result)
    }
}