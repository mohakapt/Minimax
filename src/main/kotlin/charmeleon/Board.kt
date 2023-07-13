package charmeleon

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
 * Creates an empty board of the given size.
 *
 * @param boardSize The size of the board, i.e. the number of rows and columns.
 * @return A new empty board with the given size.
 */
fun Board.Companion.empty(boardSize: Int): Board {
    require(boardSize >= 3) { "Board size must be greater than or equal to 3." }
    require(boardSize <= 8) { "Board size must be less than or equal to 8." }

    return Board(boardSize, 0b0, 0b0, Marker.X, null)
}

/**
 * Retrieves the marker at the given cell.
 *
 * @param x The x-coordinate of the cell.
 * @param y The y-coordinate of the cell.
 * @return The marker at the given cell, or `null` if the cell is empty.
 * @see Marker
 */
fun Board.markerAt(x: Int, y: Int): Marker? {
    val index = y * boardSize + x
    return when {
        stateX and (1L shl index) != 0L -> Marker.X
        stateO and (1L shl index) != 0L -> Marker.O
        else -> null
    }
}

/**
 * Checks if the board is full.
 *
 * @return `true` if the board is full, `false` otherwise.
 * @see Board.cellCount
 */
val Board.isBoardFull: Boolean
    get() = state.countOneBits() == cellCount

/**
 * Computes the current evaluation of the board.
 *
 * @return `1` if X won, `-1` if O won, `0` if the game is a tie, and `null` if the game is still in progress.
 */
val Board.evaluation: Int?
    get() {
        val winningCombinations = WinningCombinations.get(boardSize)
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
    check(evaluation == null) { "The game is already over." }
    require(move in 0..<cellCount) { "The index is out of bounds." }
    require(state and (1L shl move) == 0L) { "The cell is already occupied." }

    val newStateX = if (turn == Marker.X) stateX or (1L shl move) else stateX
    val newStateO = if (turn == Marker.O) stateO or (1L shl move) else stateO

    return Board(boardSize, newStateX, newStateO, turn.opposite, move)
}
