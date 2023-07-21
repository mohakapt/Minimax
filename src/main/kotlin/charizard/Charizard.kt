package charizard

/**
 * This is the first version of our engine, it's quite slow, but it should be unbeatable.
 * I call it Charizard because it's the third and final evolution of Charmander.
 * It can be improved further, but it's a monster as it is.
 * It uses the [minimax algorithm](https://en.wikipedia.org/wiki/Minimax) to find the best move.
 */
object Charizard {
    private val randomMoves = mapOf(
        3 to 1,
        4 to 4,
        5 to 15,
        6 to 23,
        7 to 33,
        8 to 45,
    )

    /**
     * Evaluates the given board and suggests the best move accordingly.
     *
     * @param board The board to evaluate.
     * @return The index of the suggested move.
     */
    fun suggestMove(board: Board): Int {
        val moves = board.availableMoves
        val playedMovesCount = board.cellCount - moves.size
        val randomMovesCount = randomMoves[board.boardSize] ?: 0

        if (playedMovesCount < randomMovesCount)
            return BasicStrategy.suggestMove(board)

        val evaluation = minimax(board)
        return evaluation.move ?: throw IllegalStateException("No move was found, probably the board is full.")
    }
}
