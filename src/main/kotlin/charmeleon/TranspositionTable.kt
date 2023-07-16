object TranspositionTable {
    private val table = hashMapOf<Board, Evaluation>()

    fun get(board: Board): Evaluation? {
        return table[board]
    }

    fun put(board: Board, evaluation: Evaluation) {
        table[board] = evaluation
    }

    fun clear() {
        table.clear()
    }
}
