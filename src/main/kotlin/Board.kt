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
