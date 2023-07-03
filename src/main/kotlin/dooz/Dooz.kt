package dooz

import charmander.Board
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
     * @param difficulty The difficulty of the AI.
     * @return The index of the suggested move.
     * @throws IllegalArgumentException If the cell already has an owner.
     */
    fun suggestMove(board: Board, difficulty: AiDifficulty = AiDifficulty.Hard): Int {
        val boardSize = board.boardSize
        val playerX = Player("X", "X", 0, PlayerType.Computer)
        val playerO = Player("O", "O", 1, PlayerType.Human)

        val stateX: Long = board.stateX
        val stateO: Long = board.stateO

        val gameCells = List(board.cellCount) {
            val x = it % boardSize
            val y = it / boardSize
            val player = when {
                stateX and (1L shl it) != 0L -> playerX
                stateO and (1L shl it) != 0L -> playerO
                else -> null
            }
            DoozCell(x, y, player)
        }.chunked(boardSize)

        val move = SimpleGameAi(gameCells, difficulty).play()
        return move.x + move.y * boardSize
    }
}
