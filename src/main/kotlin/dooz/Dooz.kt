package dooz

import charmander.Board
import dooz.model.AiDifficulty

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
        TODO("I will have a look at this later")
    }
}
