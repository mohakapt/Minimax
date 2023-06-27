package dooz

import charmander.Board
import dooz.ai.SimpleGameAi
import dooz.model.AiDifficulty
import dooz.model.DoozCell
import dooz.model.Player
import dooz.model.PlayerType
import charmander.Marker

/**
 * Dooz is a Tic-Tac-Toe playing AI, created by Yamin Siahmargooei.
 */
internal object Dooz {
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

        val gameCells = board.state.mapIndexed { index, player ->
            DoozCell(
                index % boardSize,
                index / boardSize,
                player?.let { if (it == Marker.X) playerX else playerO }
            )
        }.chunked(boardSize)
        val move = SimpleGameAi(gameCells = gameCells, difficulty).play()
        return move.x + move.y * boardSize
    }
}
