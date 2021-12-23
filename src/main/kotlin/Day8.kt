fun main() {
    println(countOnlyEasyNumbers("day8.txt"))
    println(sumNumbers("day8.txt"))
}

fun countOnlyEasyNumbers(filePath: String): Int {
    var result = 0
    fileAsString(filePath).split("\n")
        .forEach { encodedLine ->
            val encodings = parseEncodings(encodedLine)
            val digitOne = findDigitOne(encodings)
            val digitFour = findDigitFour(encodings)
            val digitSeven = findDigitSeven(encodings)
            val digitEight = findDigitEight(encodings)

            val numbers = parseNumbers(encodedLine)
            val oneCount = countDigits(numbers, digitOne)
            val fourCount = countDigits(numbers, digitFour)
            val sevenCount = countDigits(numbers, digitSeven)
            val eightCount = countDigits(numbers, digitEight)

            result += oneCount
            result += fourCount
            result += sevenCount
            result += eightCount
        }

    return result
}

fun sumNumbers(filePath: String): Int {
    var result = 0
    val encodingToNumeric = mutableMapOf<List<Char>, Char>()

    fileAsString(filePath).split("\n")
        .forEach { encodedLine ->
            val encodings = parseEncodings(encodedLine)
            val digitOne = findDigitOne(encodings)
            encodingToNumeric[digitOne] = '1'
            val digitFour = findDigitFour(encodings)
            encodingToNumeric[digitFour] = '4'
            val digitSeven = findDigitSeven(encodings)
            encodingToNumeric[digitSeven] = '7'
            val digitEight = findDigitEight(encodings)
            encodingToNumeric[digitEight] = '8'
            val digitNine = findDigitNine(encodings, digitFour, digitSeven)
            encodingToNumeric[digitNine] = '9'
            val mappingForE = digitEight.filterNot { digitNine.contains(it) }.first()
            val digitSix = findDigitSix(encodings, digitOne)
            encodingToNumeric[digitSix] = '6'

            val digitZero = findDigitZero(encodings, digitSix, digitNine)
            encodingToNumeric[digitZero] = '0'

            val digitThree = findDigitThree(encodings, digitOne)
            encodingToNumeric[digitThree] = '3'
            val digitTwo = findDigitTwo(encodings, mappingForE)
            encodingToNumeric[digitTwo] = '2'
            val digitFive = findDigitFive(encodings, digitThree, digitTwo)
            encodingToNumeric[digitFive] = '5'

            result += translateNumbers(parseNumbers(encodedLine), encodingToNumeric)
        }

    return result
}

private fun parseNumbers(encodedLine: String): List<List<Char>> {
    val numbers = encodedLine
        .substring(encodedLine.indexOf('|') + 2)
        .split(" ")
        .map { number -> number.toCharArray().sorted() }
    return numbers
}

private fun parseEncodings(encodedLine: String): List<List<Char>> {
    val encodings = encodedLine.substring(0, encodedLine.indexOf('|') - 1)
        .split(" ")
        .map { encoding -> encoding.toCharArray().sorted() }
    return encodings
}

private fun findDigitOne(encodings: List<List<Char>>): List<Char> = encodings.first { it.size == 2 }
private fun findDigitFour(encodings: List<List<Char>>): List<Char> = encodings.first { it.size == 4 }
private fun findDigitSeven(encodings: List<List<Char>>): List<Char> = encodings.first { it.size == 3 }
private fun findDigitEight(encodings: List<List<Char>>): List<Char> = encodings.first { it.size == 7 }
private fun findDigitZero(encodings: List<List<Char>>, digitSix: List<Char>, digitNine: List<Char>): List<Char> {
    return encodings.first { it.size == 6 && !it.containsAll(digitSix) && !it.containsAll(digitNine) }
}

private fun findDigitNine(encodings: List<List<Char>>, digitFour: List<Char>, digitSeven: List<Char>): List<Char> {
    return encodings.first { it.size == 6 && it.containsAll(digitFour) && it.containsAll(digitSeven) }
}

private fun findDigitSix(encodings: List<List<Char>>, digitOne: List<Char>): List<Char> {
    return encodings.first { it.size == 6 && !it.containsAll(digitOne) }
}

private fun findDigitThree(encodings: List<List<Char>>, digitOne: List<Char>): List<Char> {
    return encodings.first { it.size == 5 && it.containsAll(digitOne) }
}

private fun findDigitTwo(encodings: List<List<Char>>, mappingForE: Char): List<Char> {
    return encodings.first { it.size == 5 && it.contains(mappingForE) }
}

private fun findDigitFive(encodings: List<List<Char>>, digitThree: List<Char>, digitTwo: List<Char>): List<Char> {
    return encodings.first { it.size == 5 && !it.containsAll(digitThree) && !it.containsAll(digitTwo) }
}

private fun countDigits(numbers: List<List<Char>>, digit: List<Char>): Int {
    return numbers.count { it.size == digit.size && it.containsAll(digit) }
}

private fun translateNumbers(parseNumbers: List<List<Char>>, encodingToNumeric: MutableMap<List<Char>, Char>): Int {
    return parseNumbers.map { encodingToNumeric[it] }.joinToString("").toInt()
}