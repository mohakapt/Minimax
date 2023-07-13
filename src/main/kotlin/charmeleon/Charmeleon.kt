package charmeleon

/**
 * Stores the best move found by the engine, gets updated after calling [minimax].
 */
internal var awesomeMove = -1

/**
 * This is the first version of our engine, it's quite slow, but it should be unbeatable.
 * I call it Charmeleon because it's the second evolution of Charmander, It's still not a Charizard, but it's getting there.
 * It uses the [minimax algorithm](https://en.wikipedia.org/wiki/Minimax) to find the best move.
 */
object Charmeleon {
    /**
     * Evaluates the given board and suggests the best move accordingly.
     *
     * @param board The board to evaluate.
     * @return The index of the suggested move.
     */
    fun suggestMove(board: Board): Int {
        minimax(board)
        return awesomeMove
    }
}