/**
 * Represents a board of a Tic-Tac-Toe game.
 *
 * @property boardSize The size of the board, i.e. the number of rows and columns.
 * @property state The state of the board represented as a list of markers, where `null` represents an empty cell.
 * @property turn The marker of the player whose turn it is.
 * @property lastMove The index of the last move made on the board.
 * @property cellCount The number of cells on the board.
 */
data class Board(
    val boardSize: Int,
    val state: List<Marker?>,
    val turn: Marker,
    val lastMove: Int?,
) {
    val cellCount = boardSize * boardSize
}

/**
 * Computes the rows of the board.
 *
 * @return A list of lists of markers, where each outer list represents a row.
 */
val Board.rows: List<List<Marker?>>
    get() = state.chunked(boardSize)

/**
 * Computes the columns of the board.
 *
 * @return A list of lists of markers, where each outer list represents a column.
 */
val Board.columns: List<List<Marker?>>
    get() = (0..<boardSize).map { x -> state.filterIndexed { index, _ -> index % boardSize == x } }

/**
 * Computes the diagonals of the board.
 *
 * @return A list of lists of markers, where each outer list represents a diagonal.
 */
val Board.diagonals: List<List<Marker?>>
    get() = listOf(
        (0..<cellCount step boardSize + 1).map { state[it] },
        (boardSize - 1..<cellCount - boardSize + 1 step boardSize - 1).map { state[it] }
    )

/**
 * Checks if the board is full.
 *
 * @return `true` if the board is full, `false` otherwise.
 */
val Board.isBoardFull: Boolean
    get() = state.all { it != null }

/**
 * Computes the current evaluation of the board.
 *
 * @return `1` if X won, `-1` if O won, `0` if the game is a tie, and `null` if the game is still in progress.
 */
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

fun Board.makeMove(move: Int): Board {
    val newState = state.toMutableList().also { it[move] = turn }
    val newTurn = if (turn == Marker.X) Marker.X else Marker.O

    return Board(boardSize, newState, newTurn, move)
}
