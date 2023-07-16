package charmeleon

/**
 * Stores the evaluations of already evaluated boards to avoid re-evaluating them.
 *
 * @see Charmeleon.suggestMove
 */
object TranspositionTable {
    private val table = hashMapOf<Board, Evaluation>()

    /**
     * Returns the evaluation of the given board if it is stored in the table.
     *
     * @param board The board to get the evaluation for.
     * @return The evaluation of the given board if it is stored in the table, `null` otherwise.
     * @see put
     */
    fun get(board: Board): Evaluation? {
        return table[board]
    }

    /**
     * Stores the given board and its evaluation in the table.
     *
     * @param board The board to store.
     * @param evaluation The evaluation of the board.
     * @see get
     */
    fun put(board: Board, evaluation: Evaluation) {
        table[board] = evaluation
    }

    /**
     * Clears the transposition table.
     */
    fun clear() {
        table.clear()
    }
}
