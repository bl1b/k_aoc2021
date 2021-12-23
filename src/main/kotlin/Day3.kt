import kotlin.math.ceil

fun main() {
    println(determineGammeAndEpsilon("day3.txt"))
    println(determineOxygenAndCO2("day3.txt"))
}

fun determineGammeAndEpsilon(filePath: String): Int {
    val entries = parseFileIntoListOfLists(filePath)

    val nofColumns = entries[0].size - 1
    var gammaRate = ""
    var epsilonRate = ""

    for (column in 0..nofColumns) {
        val resultForColumn = mostAndLeastCommonBitInColumn(entries, column)
        gammaRate += resultForColumn.first
        epsilonRate += resultForColumn.second
    }
    return gammaRate.toInt(2) * epsilonRate.toInt(2)
}

fun determineOxygenAndCO2(filePath: String): Int {
    var entriesForOxygen = parseFileIntoListOfLists(filePath)
    var entriesForCO2 = parseFileIntoListOfLists(filePath)
    val nofColumns = entriesForOxygen[0].size - 1

    for (column in 0..nofColumns) {
        if (entriesForOxygen.size > 1) {
            val nextOxygenBit = mostAndLeastCommonBitInColumn(entriesForOxygen, column).first
            entriesForOxygen = entriesForOxygen.filter { entry -> entry[column] == nextOxygenBit }
        }
        if (entriesForCO2.size > 1) {
            val nextCO2Bit = mostAndLeastCommonBitInColumn(entriesForCO2, column).second
            entriesForCO2 = entriesForCO2.filter { entry -> entry[column] == nextCO2Bit }
        }
    }

    return entriesForOxygen[0].joinToString(separator = "").toInt(2) * entriesForCO2[0].joinToString(separator = "")
        .toInt(2)
}

private fun mostAndLeastCommonBitInColumn(lineMap: List<List<String>>, column: Int): Pair<String, String> {
    var oneCounts = 0
    for (row in lineMap) {
        if (row[column] == "1") oneCounts++
    }

    val criticalOneCount = oneCounts >= ceil(lineMap.size.div(2.toDouble())).toInt()
    return if (criticalOneCount) Pair("1", "0") else Pair("0", "1")
}

private fun parseFileIntoListOfLists(filePath: String): List<List<String>> {
    val input = fileAsString(filePath)
    val entries = mutableListOf<List<String>>()

    input
        .split("\n")
        .map { line -> line.chunked(1) }
        .forEach { lineArr -> entries.add(lineArr) }

    return entries
}