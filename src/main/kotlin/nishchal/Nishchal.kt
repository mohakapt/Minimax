package nishchal

import charmeleon.Board
import charmeleon.Marker

object Nishchal {
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
        val state = State()
        state.board = List(board.cellCount) {
            when {
                board.stateX and (1L shl it) != 0L -> "X"
                board.stateO and (1L shl it) != 0L -> "O"
                else -> null
            }
        }.chunked(board.boardSize).map { it.toTypedArray() }.toTypedArray()
        state.nextPlayer = when (board.turn) {
            Marker.X -> "X"
            Marker.O -> "O"
        }
        return state
    }
}
