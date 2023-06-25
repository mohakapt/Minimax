fun main(args: Array<String>) {
    var board = Board.empty3x3

    board.printBoard()
    val evaluation = minimax(board)
    val evaluationString = when (evaluation) {
        1 -> "win for X"
        -1 -> "win for O"
        else -> "tie"
    }
    println("The result for this position is a $evaluationString.")
    println("And the best move is ($awesomeMove):")
    board = board.makeMove(awesomeMove)
    board.printBoard()
}
