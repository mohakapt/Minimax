package charmander

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
    val stateX: Long,
    val stateO: Long,
    val turn: Marker,
    val lastMove: Int?,
) {
    companion object

    val cellCount = boardSize * boardSize
}

/**
 * Creates an empty board of 3 x 3 cells.
 *
 * @return A new empty board with the size of 9.
 */
val Board.Companion.empty3x3: Board
    get() = Board(3,0b0, 0b0, Marker.X, null)

/**
 * Creates an empty board of 4 x 4 cells.
 *
 * @return A new empty board with the size of 16.
 */
val Board.Companion.empty4x4: Board
    get() = Board(4,0b0, 0b0, Marker.X, null)

/**
 * Creates an empty board of 5 x 5 cells.
 *
 * @return A new empty board with the size of 25.
 */
val Board.Companion.empty5x5: Board
    get() = Board(5,0b0, 0b0, Marker.X, null)

/**
 * Computes the rows of the board.
 *
 * @return A list of lists of markers, where each outer list represents a row.
 */
val Board.rows: List<List<Marker?>>
    get() = TODO("I will have a look at this later")

/**
 * Computes the columns of the board.
 *
 * @return A list of lists of markers, where each outer list represents a column.
 */
val Board.columns: List<List<Marker?>>
    get() = TODO("I will have a look at this later")

/**
 * Computes the diagonals of the board.
 *
 * @return A list of lists of markers, where each outer list represents a diagonal.
 */
val Board.diagonals: List<List<Marker?>>
    get() = TODO("I will have a look at this later")

/**
 * Checks if the board is full.
 *
 * @return `true` if the board is full, `false` otherwise.
 */
val Board.isBoardFull: Boolean
    get() = TODO("I will have a look at this later")

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

/**
 * Computes the available moves on the board.
 *
 * A move is considered available if the cell is empty.
 * The cells are numbered from left to right and top to bottom,
 * Starting at `0` and ending at `boardSize * boardSize - 1`.
 *
 * For example, for a 3x3 board, the cells are numbered as follows:
 * ```
 * 0 1 2
 * 3 4 5
 * 6 7 8
 * ```
 *
 * @return A list of indices of the available cells.
 * @see Board.makeMove
 */
val Board.availableMoves: List<Int>
    get() = TODO("I will have a look at this later")

/**
 * Makes a move on the board.
 *
 * @param move The index of the cell to make the move in.
 *             Where the cells are numbered from left to right and top to bottom.
 *             Starting at `0` and ending at `boardSize * boardSize - 1`.
 * @return A new board with the move made and the turn switched.
 * @throws IllegalStateException If the game is already over.
 * @throws IllegalArgumentException If the index is out of bounds or the cell is already occupied.
 * @see Board.evaluation
 * @see Board.boardSize
 */
fun Board.makeMove(move: Int): Board {
    TODO("I will have a look at this later")
}
