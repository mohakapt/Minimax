import charmeleon.*
import dooz.Dooz
import nishchal.Nishchal
import kotlin.system.measureNanoTime

enum class Player {
    CHARMELEON,
    DOOZ,
    NISHCHAL;

    val friendlyName: String
        get() = when (this) {
            CHARMELEON -> "Charmeleon"
            DOOZ -> "Dooz"
            NISHCHAL -> "Nishchal"
        }
}

enum class StartingPlayer {
    PLAYER_1,
    PLAYER_2,
    ALTERNATE,
    RANDOM
}

class Match(
    val player1: Player,
    val player2: Player,
    val startingPlayer: StartingPlayer = StartingPlayer.ALTERNATE,
    val printBoards: Boolean = false,
    val delayMoves: Boolean = false,
) {
    private var player1Wins = 0
    private var player2Wins = 0
    private var ties = 0
    private val player1MoveTimes = mutableListOf<Long>()
    private val player2MoveTimes = mutableListOf<Long>()

    fun playMatch(boardSize: Int, gameCount: Int) {
        setupMatch()
        repeat(gameCount) {
            playGame(boardSize, it)
        }
        printMatchResults()
    }

    private fun setupMatch() {
        player1Wins = 0
        player2Wins = 0
        ties = 0
        player1MoveTimes.clear()
        player2MoveTimes.clear()
    }

    private fun playGame(boardSize: Int, gameNumber: Int) {
        val startingPlayer = when (startingPlayer) {
            StartingPlayer.PLAYER_1 -> player1
            StartingPlayer.PLAYER_2 -> player2
            StartingPlayer.ALTERNATE -> if (gameNumber % 2 == 0) player1 else player2
            StartingPlayer.RANDOM -> if (Math.random() < 0.5) player1 else player2
        }

        var board = Board.empty(boardSize)

        printBoard(board)

        while (board.score == null) {
            board = playMove(board, startingPlayer)
            printBoard(board)
        }

        updateGameResult(board, startingPlayer)
        waitForNextGame()
    }

    private fun playMove(board: Board, startingPlayer: Player): Board {
        val playingPlayer = when (board.turn) {
            Marker.X -> startingPlayer
            Marker.O -> if (startingPlayer == player1) player2 else player1
        }

        val reval: Board

        val time = measureNanoTime {
            val suggestedMove = when (playingPlayer) {
                Player.CHARMELEON -> Charmeleon.suggestMove(board)
                Player.DOOZ -> Dooz.suggestMove(board)
                Player.NISHCHAL -> Nishchal.suggestMove(board)
            }
            reval = board.makeMove(suggestedMove)
        }

        if (playingPlayer == player1)
            player1MoveTimes.add(time)
        else
            player2MoveTimes.add(time)

        return reval
    }

    private fun updateGameResult(board: Board, startingPlayer: Player) {
        val player1Won = when (board.score) {
            1 -> startingPlayer == player1
            -1 -> startingPlayer == player2
            else -> false
        }

        val player2Won = when (board.score) {
            1 -> startingPlayer == player2
            -1 -> startingPlayer == player1
            else -> false
        }

        if (player1Won) {
            player1Wins++
            println("${player1.friendlyName} wins!")
        } else if (player2Won) {
            player2Wins++
            println("${player2.friendlyName} wins!")
        } else {
            ties++
            println("Tie!")
        }
    }

    private fun printMatchResults() {
        println("Overview: \n")
        println("${player1.friendlyName}: $player1Wins")
        if (player1 != player2)
            println("${player2.friendlyName}: $player2Wins")
        println("Ties: $ties")
        println("Average ${player1.friendlyName} move time: ${player1MoveTimes.average() / 1_000_000} ms")
        if (player1 != player2)
            println("Average ${player2.friendlyName} move time: ${player2MoveTimes.average() / 1_000_000} ms")
    }

    private fun printBoard(board: Board) {
        if (printBoards) {
            if (delayMoves) Thread.sleep(200)
            clearConsole()
            board.printBoard()
        }
    }

    private fun waitForNextGame() {
        if (printBoards) {
            if (delayMoves) Thread.sleep(1000)
            clearConsole()
        }
    }
}
