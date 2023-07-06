package charmander

/**
 * Calculates the final evaluation of the given board.
 * Also, Records the best move in the global variable [awesomeMove]
 *
 * @param board The board to evaluate.
 * @param depth The depth of the current move, used to give more weight to faster wins.
 * @return `1` if the game is a win for `X`, `-1` if the game is a win for `O`, `0` if the game is a tie.
 * @see awesomeMove
 */
fun minimax(board: Board, alpha: Int = Int.MIN_VALUE, beta: Int = Int.MAX_VALUE, depth: Int = 0): Int {
    val boardEvaluation = board.evaluation
    if (boardEvaluation != null) {
        return boardEvaluation * (100 - depth)
    }

    val maximizing = board.turn == Marker.X
    var bestEvaluation = if (maximizing) Int.MIN_VALUE else Int.MAX_VALUE
    var bestMove = -1

    // The moves are shuffled to make the engine more interesting by avoiding always picking the same move.
    val moves = board.availableMoves.shuffled()

    for (move in moves) {
        val virtualBoard = board.makeMove(move)
        val moveEvaluation = minimax(virtualBoard, alpha, beta, depth + 1)

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
