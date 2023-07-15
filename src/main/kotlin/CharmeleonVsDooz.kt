import charmeleon.*
import dooz.Dooz

/**
 * This is a match between Charmeleon and Dooz playing Tic-Tac-Toe against each other.
 */
object CharmeleonVsDooz {
    /**
     * Plays a match between Charmeleon and Dooz.
     *
     * @param boardSize The size of the board, i.e. the number of rows and columns.
     * @param gameCount The number of games to play.
     */
    fun playMatch(boardSize: Int, gameCount: Int) {
        var charmeleon = 0
        var dooz = 0
        var draw = 0

        repeat(gameCount) {
            var board = Board.empty(boardSize)
            val startingPlayer = if (it % 2 == 0) Marker.X else Marker.O

            while (board.evaluation == null) {
                overflowConsole()
                Thread.sleep(200)

                val suggestedMove = if (board.turn == startingPlayer)
                    Charmeleon.suggestMove(board)
                else
                    Dooz.suggestMove(board)

                board = board.makeMove(suggestedMove)

                overflowConsole()
                board.printBoard()
            }

            val charmeleonWon =
                (board.evaluation == 1 && startingPlayer == Marker.X) || (board.evaluation == -1 && startingPlayer == Marker.O)
            val doozWon =
                (board.evaluation == -1 && startingPlayer == Marker.X) || (board.evaluation == 1 && startingPlayer == Marker.O)

            if (charmeleonWon) {
                charmeleon++
                print("Charmeleon wins!")
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
        println("Charmeleon: $charmeleon")
        println("Dooz: $dooz")
        println("Draw: $draw")
        overflowConsole()
        println()
        println()
    }
}
