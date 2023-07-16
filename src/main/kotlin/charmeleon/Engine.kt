package charmeleon

/**
 * Calculates the final evaluation of the given board.
 * Also, Records the best move in the global variable [awesomeMove]
 * so that it can be used later by the [Charmeleon] engine.
 * This is a recursive function, it calls itself to evaluate the board after each possible move.
 * To use it for the first time, you should call it with the default parameters.
 *
 * @param board The board to evaluate.
 * @param alpha The best score that the maximizing player can guarantee at that level or below.
 * @param beta The best score that the minimizing player can guarantee at that level or below.
 * @param depth The depth of the current move, used to give more weight to faster wins.
 * @return `1` if the game is a win for `X`, `-1` if the game is a win for `O`, and `0` if the game is a tie.
 * @see awesomeMove
 */
fun minimax(board: Board, depth: Int = 0, alpha: Int = Int.MIN_VALUE, beta: Int = Int.MAX_VALUE): Evaluation {
    var alpha = alpha
    var beta = beta

    val transposition = TranspositionTable.get(board)
    if (transposition != null && transposition.depth <= depth) {
        when (transposition.type) {
            NodeType.EXACT -> return transposition
            NodeType.LOWER_BOUND -> alpha = alpha.coerceAtLeast(transposition.score)
            NodeType.UPPER_BOUND -> beta = beta.coerceAtMost(transposition.score)
        }

        if (alpha >= beta)
            return transposition
    }

    val boardScore = board.score
    if (boardScore != null) {
        val adjustedScore = boardScore * (100 - depth)
        return Evaluation(adjustedScore, NodeType.EXACT, depth)
    }

    val maximizing = board.turn == Marker.X
    var bestScore = if (maximizing) Int.MIN_VALUE else Int.MAX_VALUE
    var bestMove = -1

    val moves = board.availableMoves.toMutableList()
    if (transposition?.move != null) {
        moves.remove(transposition.move)
        moves.add(0, transposition.move)
    }

    for (move in moves) {
        val virtualBoard = board.makeMove(move)
        val moveEvaluation = minimax(virtualBoard, depth + 1, alpha, beta)
        val moveScore = moveEvaluation.score

        val foundBetterMove =
            if (maximizing) {
                alpha = alpha.coerceAtLeast(moveScore)
                moveScore > bestScore
            } else {
                beta = beta.coerceAtMost(moveScore)
                moveScore < bestScore
            }

        if (foundBetterMove) {
            bestScore = moveScore
            bestMove = move
        }

        if (alpha >= beta)
            break
    }

    val nodeType = when {
        bestScore <= alpha -> NodeType.LOWER_BOUND
        bestScore >= beta -> NodeType.UPPER_BOUND
        else -> NodeType.EXACT
    }
    val evaluation = Evaluation(bestScore, nodeType, depth, bestMove)
    TranspositionTable.put(board, evaluation)

    return evaluation
}
