package charmander

/**
 * Stores the best move found by the engine, gets updated after calling [minimax].
 */
internal var awesomeMove = -1

/**
 * This is the first version of our engine, it's quite slow, but it should be unbeatable.
 * I call it Charmander because it's the first evolution of the best engine I can think of.
 * It uses the [minimax algorithm](https://en.wikipedia.org/wiki/Minimax) to find the best move.
 */
object Charmander {
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
