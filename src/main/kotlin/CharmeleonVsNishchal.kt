import charmeleon.*
import nishchal.Nishchal

/**
 * This is a match between Charmeleon and Nishchal playing Tic-Tac-Toe against each other.
 */
object CharmeleonVsNishchal {
    /**
     * Plays a match between Charmeleon and Nishchal.
     *
     * @param gameCount The number of games to play.
     */
    fun playMatch(gameCount: Int) {
        var charmeleon = 0
        var nishchal = 0
        var draw = 0

        repeat(gameCount) {
            var board = Board.empty(4)
            val startingPlayer = if (it % 2 == 0) Marker.X else Marker.O

            while (board.evaluation == null) {
                overflowConsole()
                Thread.sleep(200)

                val suggestedMove = if (board.turn == startingPlayer)
                    Charmeleon.suggestMove(board)
                else
                    Nishchal.suggestMove(board)

                board = board.makeMove(suggestedMove)

                overflowConsole()
                board.printBoard()
            }

            val charmeleonWon =
                (board.evaluation == 1 && startingPlayer == Marker.X) || (board.evaluation == -1 && startingPlayer == Marker.O)
            val nishchalWon =
                (board.evaluation == -1 && startingPlayer == Marker.X) || (board.evaluation == 1 && startingPlayer == Marker.O)

            if (charmeleonWon) {
                charmeleon++
                print("Charmeleon wins!")
            } else if (nishchalWon) {
                nishchal++
                print("Nishchal wins!")
            } else {
                draw++
                print("Draw!")
            }

            overflowConsole()
            Thread.sleep(1000)
        }

        println("Overview: \n")
        println("Charmeleon: $charmeleon")
        println("Nishchal: $nishchal")
        println("Draw: $draw")
        overflowConsole()
        println()
        println()
    }
}

private fun overflowConsole() {
    println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n")
}
