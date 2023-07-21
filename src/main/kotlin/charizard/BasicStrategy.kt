package charizard

object BasicStrategy {
    fun suggestMove(board: Board): Int {
        val moves = board.availableMoves

        winIfPossible(board, moves)?.let { return it }
        return moves.random()

    private fun winIfPossible(board: Board, moves: List<Int>): Int? {
        for (move in moves) {
            val score = board.makeMove(move).score
            val win = score == 1 && board.turn == Marker.X || score == -1 && board.turn == Marker.O
            if (win) return move
        }
        return null
    }
    }
}
