class Board(val id: Int, private val positions: List<List<BoardPosition>> = mutableListOf()) {
    fun markPositionHavingValue(value: Int) {
        positions.forEach { row ->
            row.firstOrNull { col -> col.value == value }?.isMarked = true
        }
    }

    fun isWinning(): Boolean {
        return hasWinningRow() || hasWinningCol()
    }

    fun sumOfUnmarkedPositions(): Int {
        var sum = 0
        positions.forEach { row ->
            row.forEach { col ->
                if (!col.isMarked) {
                    sum += col.value
                }
            }
        }
        return sum
    }

    fun hasWinningRow(): Boolean {
        var isWinning = true
        positions.forEach rowLoop@{ row ->
            isWinning = true
            row.forEach colLoop@{ col ->
                if (!col.isMarked) {
                    isWinning = false
                    return@colLoop
                }
            }
            if (isWinning) return true
        }
        return isWinning
    }

    fun hasWinningCol(): Boolean {
        val maxColIndex = positions.first().size
        var isWinning = true
        for (colIndex in 0 until maxColIndex) {
            isWinning = true
            positions.forEach rowLoop@{ row ->
                if (!row[colIndex].isMarked) {
                    isWinning = false
                    return@rowLoop
                }
            }
            if (isWinning) break
        }
        return isWinning
    }

    override fun toString(): String {
        var boardString = "--------------\n"
        boardString += positions.map {
            it.map boardMap@{ boardPosition ->
                var stringVal = boardPosition.value.toString()
                if (boardPosition.isMarked) {
                    stringVal += "*"
                }
                stringVal = stringVal.padStart(3)
                return@boardMap stringVal
            }.joinToString(separator = " ")
        }.joinToString(separator = "\n")
        boardString += "\n--------------\n"
        return boardString
    }
}

data class BoardPosition(val value: Int, var isMarked: Boolean = false)

fun main() {
    println(sumUnmarkedOnWinningBoard("day4.txt"))
    println(sumUnmarkedOnLastWinningBoard("day4.txt"))
}

fun sumUnmarkedOnWinningBoard(filePath: String): Int {
    val lines = fileAsString(filePath).split("\n")
    val drawings = initDrawings(lines)
    val boards = initBoards(lines)

    var result = 0
    for (drawing in drawings) {
        for (board in boards) {
            board.markPositionHavingValue(drawing)
            if (board.isWinning()) {
                result = board.sumOfUnmarkedPositions() * drawing
                break
            }
        }
        if (result != 0) {
            break
        }
    }
    return result
}

fun sumUnmarkedOnLastWinningBoard(filePath: String): Int {
    val lines = fileAsString(filePath).split("\n")
    val drawings = initDrawings(lines)
    val boards = initBoards(lines)

    var result = 0
    for (drawing in drawings) {
        for (board in boards) {
            if (board.isWinning()) continue

            board.markPositionHavingValue(drawing)
            if (board.isWinning()) {
                result = board.sumOfUnmarkedPositions() * drawing
            }
        }
    }
    return result
}

private fun initBoards(lines: List<String>): MutableList<Board> {
    val boardData = lines.subList(1, lines.size).chunked(6, { it.takeLast(5) })
    val boards = mutableListOf<Board>()

    for (boardId in boardData.indices) {
        val positions = mutableListOf<List<BoardPosition>>()
        boardData[boardId].forEach { boardRow ->
            val row = boardRow.split(" ").map { it.trim() }.toMutableList()
            row.removeIf { it.isEmpty() }
            positions.add(row.map { BoardPosition(it.toInt()) })
        }
        boards.add(Board(boardId, positions))
    }
    return boards
}

private fun initDrawings(lines: List<String>): List<Int> {
    val drawings = lines[0].split(",").map { it.trim().toInt() }
    return drawings
}