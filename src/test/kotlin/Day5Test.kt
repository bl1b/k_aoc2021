import org.junit.jupiter.api.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

internal class Day5Test {
    @Test
    fun testOverlapsWithoutDiagonal() {
        val actual = numberOfPointsWith2Overlaps("day5_test.txt", false)
        assertEquals(5, actual)
    }

    @Test
    fun testOverlapsWithDiagonal() {
        val actual = numberOfPointsWith2Overlaps("day5_test.txt", true)
        assertEquals(12, actual)
    }

    @Test
    fun determineDiagonal() {
        val line = Line(Point(9, 7), Point(7, 9))
        val points = line.pointsCovered(true).toTypedArray()
        assertContains(points, Point(9, 7))
        assertContains(points, Point(8, 8))
        assertContains(points, Point(7, 9))

        val line2 = Line(Point(1, 1), Point(3, 3))
        val points2 = line2.pointsCovered(true).toTypedArray()
        assertContains(points2, Point(1, 1))
        assertContains(points2, Point(2, 2))
        assertContains(points2, Point(3, 3))
    }
}