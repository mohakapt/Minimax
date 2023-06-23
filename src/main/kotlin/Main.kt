

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}


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
