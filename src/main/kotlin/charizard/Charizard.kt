package charizard

/**
 * This is the first version of our engine, it's quite slow, but it should be unbeatable.
 * I call it Charizard because it's the third and final evolution of Charmander.
 * It can be improved further, but it's a monster as it is.
 * It uses the [minimax algorithm](https://en.wikipedia.org/wiki/Minimax) to find the best move.
 */
object Charizard {
    private val basicStrategyMoves = mapOf(
        3 to 1,
        4 to 4,
        5 to 14,
        6 to 23,
        7 to 35,
        8 to 48,
    )


    /**
     * Evaluates the given board and returns an [Evaluation] object, containing the best move and the score.
     * The score is `1` if the game is a win for X, `-1` if the game is a win for O, and `0` if the game is a tie.
     * The move is `null` if the game is over, otherwise it's the index of the best move.
     *
     * @param board The board to evaluate.
     * @return The evaluation of the board.
     * @see Evaluation
     */
    fun evaluate(board: Board): Evaluation {
        val moves = board.availableMoves
        val playedMoveCount = board.cellCount - moves.size
        val basicStrategyMoveCount = basicStrategyMoves[board.boardSize] ?: 0

        if (playedMoveCount < basicStrategyMoveCount)
            return Evaluation(0, NodeType.EXACT, 0, BasicStrategy.suggestMove(board))

        return minimax(board)
    }

    /**
     * Evaluates the given board and suggests the best move accordingly.
     *
     * @param board The board to evaluate.
     * @return The index of the suggested move.
     */
    fun suggestMove(board: Board): Int {
        return evaluate(board).move ?: throw IllegalStateException("No move was found, probably the board is full.")
    }
}
