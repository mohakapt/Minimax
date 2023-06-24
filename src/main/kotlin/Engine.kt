var awesomeMove = -1

/**
 * Calculates the final evaluation of the given board.
 * Also, Records the best move in the global variable `awesomeMove`
 *
 * @param board The board to evaluate.
 * @return `1` if the game is a win for `X`, `-1` if the game is a win for `O`, `0` if the game is a tie.
 * @see awesomeMove
 */
fun minimax(board: Board): Int {
    val boardEvaluation = board.evaluation
    if (boardEvaluation != null) {
        return boardEvaluation
    }

    val maximizing = board.turn == Marker.X
    var bestEvaluation = if (maximizing) -1 else 1
    var bestMove = -1

    val moves = board.availableMoves.shuffled()

    for (move in moves) {
        val virtualBoard = board.makeMove(move)
        val moveEvaluation = minimax(virtualBoard)

        val foundBetterMove =
            if (maximizing) moveEvaluation > bestEvaluation
            else moveEvaluation < bestEvaluation

        if (foundBetterMove) {
            bestEvaluation = moveEvaluation
            bestMove = move
        }
    }

    awesomeMove = bestMove
    return bestEvaluation
}
