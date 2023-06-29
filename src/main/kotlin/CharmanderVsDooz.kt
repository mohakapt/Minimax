import charmander.*
import dooz.Dooz

/**
 * This is a match between Charmander and Dooz playing Tic-Tac-Toe against each other.
 */
object CharmanderVsDooz {
    /**
     * Plays a match between Charmander and Dooz.
     *
     * @param gameCount The number of games to play.
     */
    fun playMatch(gameCount: Int) {
        var charmander = 0
        var dooz = 0
        var draw = 0

        repeat(gameCount) {
            var board = Board.empty3x3
            val startingPlayer = if (it % 2 == 0) Marker.X else Marker.O

            while (board.evaluation == null) {
                overflowConsole()
                Thread.sleep(200)

                val suggestedMove = if (board.turn == startingPlayer)
                    Charmander.suggestMove(board)
                else
                    Dooz.suggestMove(board)

                board = board.makeMove(suggestedMove)

                overflowConsole()
                board.printBoard()
            }

            val charmanderWon =
                (board.evaluation == 1 && startingPlayer == Marker.X) || (board.evaluation == -1 && startingPlayer == Marker.O)
            val doozWon =
                (board.evaluation == -1 && startingPlayer == Marker.X) || (board.evaluation == 1 && startingPlayer == Marker.O)

            if (charmanderWon) {
                charmander++
                print("Charmander wins!")
            } else if (doozWon) {
                dooz++
                print("Dooz wins!")
            } else {
                draw++
                print("Draw!")
            }

            overflowConsole()
            Thread.sleep(1000)
        }

        println("Overview: \n")
        println("Charmander: $charmander")
        println("Dooz: $dooz")
        println("Draw: $draw")
        overflowConsole()
        println()
        println()
    }
}

private fun overflowConsole() {
    println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n")
}
