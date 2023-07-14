package dooz

import charmeleon.Board
import charmeleon.Marker
import charmeleon.coordinatesOf
import charmeleon.markerAt
import dooz.ai.SimpleGameAi
import dooz.model.AiDifficulty
import dooz.model.DoozCell
import dooz.model.Player
import dooz.model.PlayerType

/**
 * Dooz is a Tic-Tac-Toe playing AI, created by Yamin Siahmargooei.
 */
object Dooz {
    /**
     * Converts our board to a format that the Dooz AI can understand, then asks the AI to suggest a move.
     *
     * @param board The board to suggest a move for.
     * @return The index of the suggested move.
     * @throws IllegalArgumentException If the cell already has an owner.
     */
    fun suggestMove(board: Board): Int {
        val boardSize = board.boardSize
        val gameCells = boardToGameCells(board)

        val move = SimpleGameAi(gameCells, AiDifficulty.Hard).play()
        return move.x + move.y * boardSize
    }

    private fun boardToGameCells(board: Board): List<List<DoozCell>> {
        val boardSize = board.boardSize

        return List(board.cellCount) {
            val (x, y) = board.coordinatesOf(it)
            val player = board.markerAt(x, y)?.asDoozPlayer
            DoozCell(x, y, player)
        }.chunked(boardSize)
    }

    private val playerX = Player("X", "X", 0, PlayerType.Computer)
    private val playerO = Player("O", "O", 1, PlayerType.Human)

    private val Marker.asDoozPlayer: Player
        get() = when (this) {
            Marker.X -> playerX
            Marker.O -> playerO
        }
}
