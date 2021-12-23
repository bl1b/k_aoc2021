import java.math.BigInteger

class Lanternfish(
    var daysUntilReProduction: Int,
    val dayBorn: Int
) {

    fun countMyDescendants(days: Int): BigInteger {
        var birthDayOfDescendant = dayBorn + daysUntilReProduction + 1
        var numberOfDescendants = BigInteger.ZERO
        while (birthDayOfDescendant <= days) {
            numberOfDescendants++
            val newFish = Lanternfish(8, birthDayOfDescendant)
            numberOfDescendants += newFish.countMyDescendants(days)
            birthDayOfDescendant += 7
        }
        return numberOfDescendants
    }
}

fun main() {
    println(spawnLanternfish("day6.txt", 80))
    println(spawnLanternfish("day6.txt", 256))
}

fun spawnLanternfish(filePath: String, days: Int): BigInteger {
    val fishes = fileAsString(filePath)
        .split(",")
        .map { it.trim().toInt() }
    val countMap = mutableMapOf<Int, BigInteger>()
    var descendantCount = BigInteger.ZERO
    fishes.forEach { fish ->
        if (!countMap.containsKey(fish)) {
            countMap[fish] = Lanternfish(fish, 0).countMyDescendants(days)
        }
        descendantCount += countMap[fish]!!
    }

    return descendantCount.plus(BigInteger.valueOf(fishes.size.toLong()))
}