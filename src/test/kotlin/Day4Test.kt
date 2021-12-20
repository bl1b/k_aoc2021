import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class Day4Test {

    companion object {
        private val ROW_1_MIXED = listOf(BoardPosition(0, false), BoardPosition(1, false), BoardPosition(2, true))
        private val ROW_2_MARKED = listOf(BoardPosition(3, true), BoardPosition(4, true), BoardPosition(5, true))
        private val ROW3_MIXED = listOf(BoardPosition(6, false), BoardPosition(7, true), BoardPosition(8, false))

        private val ROW2_MIXED = listOf(BoardPosition(3, false), BoardPosition(4, false), BoardPosition(5, true))
        private val ROW3_MIXED_2 = listOf(BoardPosition(6, false), BoardPosition(7, true), BoardPosition(8, true))
    }


    @Test
    fun testUnmarkedOfWinningBoard() {
        val result = sumUnmarkedOnWinningBoard("day4_test.txt")
        assertEquals(4512, result)
    }

    @Test
    fun testUnmarkedOfLatestWinningBoard() {
        val result = sumUnmarkedOnLastWinningBoard("day4_test.txt")
        assertEquals(1924, result)
    }

    @Test
    fun testRowIsValidated() {
        val board = Board(0, listOf(ROW_1_MIXED, ROW_2_MARKED, ROW3_MIXED))
        assertTrue(board.hasWinningRow())
        assertFalse(board.hasWinningCol())
    }

    @Test
    fun testColIsValidated() {
        val board = Board(1, listOf(ROW_1_MIXED, ROW2_MIXED, ROW3_MIXED_2))
        assertFalse { board.hasWinningRow() }
        assertTrue { board.hasWinningCol() }
    }

    @Test
    fun testNoWinner() {
        val board = Board(2, listOf(ROW_1_MIXED, ROW2_MIXED, ROW3_MIXED))
        assertFalse { board.hasWinningRow() }
        assertFalse { board.hasWinningCol() }
    }
}