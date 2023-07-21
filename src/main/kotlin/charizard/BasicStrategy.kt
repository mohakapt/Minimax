package charizard

object BasicStrategy {
    fun suggestMove(board: Board): Int {
        val moves = board.availableMoves

        winIfPossible(board, moves)?.let { return it }
        blockIfNecessary(board, moves)?.let { return it }
        return moves.random()

    private fun winIfPossible(board: Board, moves: List<Int>): Int? {
        for (move in moves) {
            val score = board.makeMove(move).score
            val win = score == 1 && board.turn == Marker.X || score == -1 && board.turn == Marker.O
            if (win) return move
        }
        return null
    }

    private fun blockIfNecessary(board: Board, moves: List<Int>): Int? {
        for (currentMove in moves) {
            val newBoard = board.makeMove(currentMove)
            for (nextMove in newBoard.availableMoves) {
                val score = newBoard.makeMove(nextMove).score
                val block = score == 1 && board.turn == Marker.O || score == -1 && board.turn == Marker.X
                if (block) return nextMove
            }
        }
        return null
    }
    }
}
