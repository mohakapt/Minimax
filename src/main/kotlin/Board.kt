data class Board(
    val boardSize: Int,
    val state: List<Marker?>,
    val turn: Marker,
    val lastMove: Int?,
) {
    val cellCount = boardSize * boardSize
}

val Board.rows: List<List<Marker?>>
    get() = state.chunked(boardSize)

val Board.columns: List<List<Marker?>>
    get() = (0..<boardSize).map { x -> state.filterIndexed { index, _ -> index % boardSize == x } }

val Board.diagonals: List<List<Marker?>>
    get() = listOf(
        (0..<cellCount step boardSize + 1).map { state[it] },
        (boardSize - 1..<cellCount - boardSize + 1 step boardSize - 1).map { state[it] }
    )

val Board.isBoardFull: Boolean
    get() = state.all { it != null }

val Board.evaluation: Int?
    get() {
        val combinations = rows + columns + diagonals

        val xWon = combinations.any { combination -> combination.all { it == Marker.X } }
        if (xWon) return 1

        val oWon = combinations.any { combination -> combination.all { it == Marker.O } }
        if (oWon) return -1

        if (isBoardFull) return 0

        return null
    }
