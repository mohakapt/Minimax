import charmeleon.*
import kotlin.system.measureNanoTime

fun main(args: Array<String>) {
    CharmeleonVsDooz.playMatch(4, 10)
//    testEngine()
}

private fun testEngine() {
    var board = Board.empty(4)
    var evaluation: Int

    board.printBoard()
    val time = measureNanoTime {
        evaluation = minimax(board)
    }

    val evaluationString = when (evaluation) {
        1 -> "win for X"
        -1 -> "win for O"
        else -> "tie"
    }
    println("The evaluation took ${time / 1_000_000} ms.")
    println("The result for this position is a $evaluationString.")
    println("And the best move is ($awesomeMove):")
    board = board.makeMove(awesomeMove)
    board.printBoard()
}
