import charmeleon.*
import kotlin.system.measureNanoTime

object CharmeleonVsCharmeleon {
    fun playMatch(boardSize: Int, gameCount: Int) {
        var win = 0
        var draw = 0
        val moveTimes = mutableListOf<Long>()

        repeat(gameCount) {
            var board = Board.empty(boardSize)

            if (printBoards) {
                overflowConsole()
                Thread.sleep(200)
                board.printBoard()
            }

            while (board.score == null) {
                val time = measureNanoTime {
                    val suggestedMove = Charmeleon.suggestMove(board)
                    board = board.makeMove(suggestedMove)
                }
                moveTimes.add(time)

                if (printBoards) {
                    overflowConsole()
                    board.printBoard()
                }
            }

            if (board.score == 0) {
                draw++
                print("Draw!")
            } else {
                win++
                print("Win!")
            }

            if (printBoards) {
                overflowConsole()
                Thread.sleep(1000)
            }
        }

        println("Overview: \n")
        println("Win: $win")
        println("Draw: $draw")
        println("Average move time: ${moveTimes.average() / 1_000_000} ms")
        overflowConsole()
    }
}
