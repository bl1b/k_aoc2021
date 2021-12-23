import kotlin.math.abs

fun main() {
    println(calculateMinimumFuelConsumption("day7.txt", ::linearConsumptionFunc))
    println(calculateMinimumFuelConsumption("day7.txt", ::exponentialConsumptionFunc))
}

fun calculateMinimumFuelConsumption(filePath: String, consumptionFunc: (Int, Int) -> Int): Int {
    val positions = fileAsString(filePath).split(",").map { it.trim().toInt() }
    val minPosition = positions.minOrNull()!!
    val maxPosition = positions.maxOrNull()!!

    val fuelConsumptions = mutableListOf<Int>()

    for (referencePosition in minPosition..maxPosition) {
        var consumptionForReference = 0
        for (position in positions) {
            consumptionForReference += consumptionFunc(position, referencePosition)
        }
        fuelConsumptions.add(consumptionForReference)
    }

    return fuelConsumptions.minOrNull()!!
}

fun linearConsumptionFunc(positionFrom: Int, positionTo: Int): Int = positionDelta(positionFrom, positionTo)

fun exponentialConsumptionFunc(positionFrom: Int, positionTo: Int): Int {
    val d = positionDelta(positionFrom, positionTo)
    return (d * (1 + ((d - 1) * 0.5))).toInt()
}

private fun positionDelta(from: Int, to: Int): Int = abs(from - to)