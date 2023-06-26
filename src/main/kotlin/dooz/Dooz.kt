package dooz

import Board
import dooz.ai.SimpleGameAi
import dooz.model.AiDifficulty
import dooz.model.DoozCell
import dooz.model.Player
import dooz.model.PlayerType

internal object Dooz {
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
