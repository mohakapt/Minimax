package nishchal

import charizard.Board
import charizard.markerAt

/**
 * Nishchal is a Tic-Tac-Toe playing AI, created by Nishchal Nagar.
 */
object Nishchal {
    /**
     * Converts our board to a format that Nishchal AI can understand, then asks the AI to suggest a move.
     *
     * @param board The board to suggest a move for.
     * @return The index of the suggested move.
     */
    fun suggestMove(board: Board): Int {
        val state = boardToState(board)
        val newState = TicTacToeAlphaBeta().nextStateToMove(state)

        val oldArray = state.board.flatten()
        val newArray = newState.board.flatten()

        for (i in oldArray.indices)
            if (oldArray[i] != newArray[i]) return i

        return -1
    }

    private fun boardToState(board: Board): State {
        val state = List(board.cellCount) { board.markerAt(it)?.asString }
            .chunked(board.boardSize)
            .map { it.toTypedArray() }.toTypedArray()

        return State().also {
            it.board = state
            it.nextPlayer = board.turn.asString
        }
    }
}
