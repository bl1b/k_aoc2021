import java.util.regex.Pattern
import kotlin.math.abs

val linePattern = Pattern.compile("^(\\d+),(\\d+) -> (\\d+),(\\d+)$")

data class Point(val x: Int, val y: Int)

class Line(val pointFrom: Point, val pointTo: Point) {

    fun pointsCovered(includeDiagonal: Boolean = false): List<Point> {
        var points = mutableListOf<Point>()
        if (isHorizontalLine()) {
            for (x in minOf(pointFrom.x, pointTo.x)..maxOf(pointFrom.x, pointTo.x)) {
                points.add(Point(x, pointFrom.y))
            }
        }
        if (isVerticalLine()) {
            for (y in minOf(pointFrom.y, pointTo.y)..maxOf(pointFrom.y, pointTo.y)) {
                points.add(Point(pointFrom.x, y))
            }
        }
        if (includeDiagonal && isDiagonalLine()) {
            val delta = abs(pointFrom.x - pointTo.x)
            val xModifier = if (pointFrom.x > pointTo.x) -1 else 1
            val yModifier = if (pointFrom.y > pointTo.y) -1 else 1
            for (index in 0..delta) {
                points.add(Point(pointFrom.x + (index * xModifier), pointFrom.y + (index * yModifier)))
            }
        }
        return points
    }

    private fun isHorizontalLine(): Boolean = pointFrom.y == pointTo.y

    private fun isVerticalLine(): Boolean = pointFrom.x == pointTo.x

    private fun isDiagonalLine(): Boolean {
        return abs(pointTo.x - pointFrom.x) == abs(pointTo.y - pointFrom.y)
    }
}

fun main() {
    println(numberOfPointsWith2Overlaps("day5.txt", false))
    println(numberOfPointsWith2Overlaps("day5.txt", true))
}

fun numberOfPointsWith2Overlaps(filePath: String, includeDiagonal: Boolean): Int {
    val lines = fileAsString(filePath).split("\n").map { parseCoordinateLine(it) }
    val hitPoints = mutableMapOf<Point, Int>()
    lines.forEach { line ->
        line.pointsCovered(includeDiagonal).forEach { pointCovered ->
            if (hitPoints.containsKey(pointCovered)) {
                hitPoints[pointCovered] = hitPoints[pointCovered]!!.inc()
            } else {
                hitPoints[pointCovered] = 1
            }
        }
    }
    return hitPoints.values.reduce { acc, i -> if (i > 1) acc + 1 else acc + 0 } - 1
}

private fun parseCoordinateLine(line: String): Line {
    val m = linePattern.matcher(line)
    if (!m.matches()) {
        throw IllegalStateException("$line to not match Pattern ${linePattern.pattern()}!")
    }
    return Line(Point(m.group(1).toInt(), m.group(2).toInt()), Point(m.group(3).toInt(), m.group(4).toInt()))
}