package charizard

/**
 * Calculates the final evaluation of the given board.
 * This is a recursive function, it calls itself to evaluate the board after each possible move.
 * To use it for the first time, you should call it with the default parameters.
 *
 * @param board The board to evaluate.
 * @param alpha The best score that the maximizing player can guarantee at that level or below.
 * @param beta The best score that the minimizing player can guarantee at that level or below.
 * @param depth The depth of the current move, used to give more weight to faster wins.
 * @return The best score of the board and the best move to get that score.
 * @see score
 * @see Evaluation
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

        if (alpha >= beta) {
            val nodeType = determineNodeType(transposition.score, alpha, beta)
            return Evaluation(transposition.score, nodeType, depth)
        }
    }

    val boardScore = board.score
    if (boardScore != null) {
        val adjustedScore = boardScore * (100 - depth)
        val nodeType = determineNodeType(adjustedScore, alpha, beta)
        return Evaluation(adjustedScore, nodeType, depth)
    }

    val maximizing = board.turn == Marker.X
    var bestScore = if (maximizing) Int.MIN_VALUE else Int.MAX_VALUE
    var bestMove = -1

    val moves = board.availableMoves.shuffled().toMutableList()
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

    val nodeType = determineNodeType(bestScore, alpha, beta)
    val evaluation = Evaluation(bestScore, nodeType, depth, bestMove)
    TranspositionTable.put(board, evaluation)

    return evaluation
}

private fun determineNodeType(score: Int, alpha: Int, beta: Int): NodeType {
    return when {
        score <= alpha -> NodeType.UPPER_BOUND
        score >= beta -> NodeType.LOWER_BOUND
        else -> NodeType.EXACT
    }
}
