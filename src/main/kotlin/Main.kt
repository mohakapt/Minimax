import charizard.*

fun main(args: Array<String>) {
    Match(Player.CHARIZARD, Player.DOOZ, printBoards = true)
        .playMatch(4, 1000)

    solveCoverGame()
}

val Board.Companion.coverGame: Board
    get() = Board.empty(4)
        .makeMove(6)    // X
        .makeMove(2)    // O
        .makeMove(5)    // X
        .makeMove(0)    // O
        .makeMove(8)    // X
        .makeMove(1)    // O
        .makeMove(3)    // X
        .makeMove(14)   // O - a mistake by O, now X can force a win, any other move would have been a draw.

fun solveCoverGame() {
    var board = Board.coverGame
    while (board.score == null) {
        board.printBoard()
        board = board.makeMove(Charizard.suggestMove(board))
    }
    board.printBoard()
    println("X Wins!")
}
