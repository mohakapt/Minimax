
fun minimax(board: Board): Int {
    val boardEvaluation = board.evaluation
    if (boardEvaluation != null) {
        return boardEvaluation
    }

    val moves = board.availableMoves.shuffled()

    for (move in moves) {
        val virtualBoard = board.makeMove(move)
        val moveEvaluation = minimax(virtualBoard)

    }

    TODO("Implement minimax algorithm.")
}
