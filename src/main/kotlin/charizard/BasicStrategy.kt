package charizard

/**
 * A basic strategy to follow when playing and the search tree is too large to explore by minimax.
 * The strategy works as follows:
 * 1. If there is a move that wins the game, make that move.
 * 2. If there is a move that blocks the opponent from winning on the next turn, make that move.
 * 3. If the center is available, take it.
 * 4. If a corner is available, take it.
 * 5. If the board has any remaining winning combinations, make a move that works towards completing the most completed one of them.
 * 6. Otherwise, make a random move.
 */
object BasicStrategy {
    /**
     * Suggest a move for the given board based on the basic strategy.
     * This method is not guaranteed to return the best move, but it's fast and should be better than random playing.
     *
     * @param board The board to evaluate.
     * @return The index of the suggested move.
     */
    fun suggestMove(board: Board): Int {
        val moves = board.availableMoves

        winIfPossible(board, moves)?.let { return it }
        blockIfNecessary(board, moves)?.let { return it }
        takeCenterOrCorner(board, moves)?.let { return it }
        workTowardsNearestWin(board, moves)?.let { return it }
        return moves.random()
    }

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

    private fun takeCenterOrCorner(board: Board, moves: List<Int>): Int? {
        val boardSize = board.boardSize
        val cellCount = board.cellCount

        val centers = mutableListOf<Int>()
        if (boardSize % 2 == 1) {
            centers.add(cellCount / 2)
        } else {
            centers.add(cellCount / 2 - boardSize / 2)
            centers.add(cellCount / 2 - boardSize / 2 - 1)
            centers.add(cellCount / 2 + boardSize / 2)
            centers.add(cellCount / 2 + boardSize / 2 - 1)
        }
        for (move in centers.shuffled()) {
            if (move in moves)
                return move
        }

        val corners = mutableListOf(
            0,
            boardSize - 1,
            boardSize * (boardSize - 1),
            cellCount - 1,
        )
        for (move in corners.shuffled()) {
            if (move in moves)
                return move
        }

        return null
    }

    private fun workTowardsNearestWin(board: Board, moves: List<Int>): Int? {
        val state = if (board.turn == Marker.X) board.stateX else board.stateO
        val opponentState = if (board.turn == Marker.X) board.stateO else board.stateX
        val winningCombinations = WinningCombinations.get(board.boardSize)
            .filter { (it and opponentState) == 0L }
            .sortedBy { (it and state).countOneBits() }

        for (combination in winningCombinations) {
            val cells = mutableListOf<Int>()
            for (i in 0..<board.cellCount) {
                val cell = 1L shl i
                if (combination and cell == cell)
                    cells.add(i)
            }

            for (move in cells.shuffled()) {
                if (move in moves)
                    return move
            }
        }

        return null
    }
}
