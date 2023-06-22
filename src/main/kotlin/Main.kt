

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

    TODO("Implement minimax algorithm.")
}
