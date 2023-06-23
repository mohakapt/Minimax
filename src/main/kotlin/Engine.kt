var awesomeMove = -1

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
