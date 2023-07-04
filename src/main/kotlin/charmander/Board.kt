package charmander

/**
 * Represents a board of a Tic-Tac-Toe game.
 *
 * @property boardSize The size of the board, i.e. the number of rows and columns.
 * @property stateX The state of the board for player `X` as a bitboard, where each bit represents a cell.
 * @property stateO The state of the board for player `O` as a bitboard, where each bit represents a cell.
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

    /**
     * Returns the state of the board as a bitboard.
     *
     * @return The state of the board as a bitboard.
     */
    val state: Long
        get() = stateX or stateO
}

/**
 * Creates an empty board of 3 x 3 cells.
 *
 * @return A new empty board with the size of 9.
 */
val Board.Companion.empty3x3: Board
    get() = Board(3, 0b0, 0b0, Marker.X, null)

/**
 * Creates an empty board of 4 x 4 cells.
 *
 * @return A new empty board with the size of 16.
 */
val Board.Companion.empty4x4: Board
    get() = Board(4, 0b0, 0b0, Marker.X, null)

/**
 * Creates an empty board of 5 x 5 cells.
 *
 * @return A new empty board with the size of 25.
 */
val Board.Companion.empty5x5: Board
    get() = Board(5, 0b0, 0b0, Marker.X, null)

/**
 * Stores the winning combinations for a 3x3 board as bitboards.
 * This is a temporary solution until I find a better way to do this.
 */
private val winningCombinations = getWinningCombinations(3)

private fun getWinningCombinations(boardSize: Int): List<Long> {
    val wins = mutableListOf<Long>()
    val cellCount = boardSize * boardSize

    for (i in 0..<cellCount) {
        val row = i / boardSize
        val col = i % boardSize

        // Row combination
        if (col <= 0)
            wins.add(((1L shl boardSize) - 1) shl (row * boardSize + col))

        // Column combination
        if (row <= 0) {
            val columnMask = (1..<boardSize).fold(1L) { acc, j -> acc or (1L shl (j * boardSize)) }
            wins.add(columnMask shl i)
        }

        // Diagonal combination (from top-left to bottom-right)
        if (row == col && row <= 0)
            wins.add((1..<boardSize).fold(1L) { acc, j -> acc or (1L shl (j * (boardSize + 1))) })

        // Diagonal combination (from top-right to bottom-left)
        if (row + col == boardSize - 1 && row <= 0)
            wins.add((1..<boardSize).fold(1L shl (boardSize - 1)) { acc, j -> acc or (1L shl ((j + 1) * (boardSize - 1))) })
    }

    return wins
}

/**
 * Checks if the board is full.
 *
 * @return `true` if the board is full, `false` otherwise.
 * @see Board.cellCount
 */
val Board.isBoardFull: Boolean
    get() {
        return state.countOneBits() == cellCount
    }

/**
 * Computes the current evaluation of the board.
 *
 * @return `1` if X won, `-1` if O won, `0` if the game is a tie, and `null` if the game is still in progress.
 */
val Board.evaluation: Int?
    get() {
        for (combination in winningCombinations) {
            if (stateX and combination == combination) return 1
            if (stateO and combination == combination) return -1
        }

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
    get() {
        val moves = mutableListOf<Int>()

        repeat(cellCount) {
            if (state and (1L shl it) == 0L)
                moves.add(it)
        }

        return moves
    }

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
    if (evaluation != null)
        throw IllegalStateException("The game is already over.")
    if (move !in 0..<cellCount)
        throw IllegalArgumentException("The index is out of bounds.")
    if (state and (1L shl move) != 0L)
        throw IllegalArgumentException("The cell is already occupied.")

    val newStateX = if (turn == Marker.X) stateX or (1L shl move) else stateX
    val newStateO = if (turn == Marker.O) stateO or (1L shl move) else stateO

    return Board(boardSize, newStateX, newStateO, turn.opposite, move)
}
