fun main(args: Array<String>) {
    val result = runDay1("day1.txt")
    println(result)

    val result2 = runDay1PartTwo("day1.txt")
    println(result2)
}

fun runDay1(filePath: String): Int {
    val input = fileAsString(filePath)
    val split = input.split("\n").map { line -> line.toInt() }
    return countNumberOfIncreases(split)
}

fun runDay1PartTwo(filePath: String): Int {
    val input = fileAsString(filePath)
    val split = input.split("\n").map { line -> line.toInt() }
    val sums = mutableListOf<Int>()

    split.forEachIndexed { index, _ ->
        run {
            if (index + 2 < split.size) {
                sums.add(split[index] + split[index + 1] + split[index + 2])
            }
        }
    }
    return countNumberOfIncreases(sums)
}

private fun countNumberOfIncreases(numbers: List<Int>): Int =
    numbers.foldIndexed(0) { index, acc, cur ->
        acc + if (index > 0 && numbers[index - 1] < cur) 1 else 0
    }

