package charmander

private fun Board.outputCell(index: Int, cell: Marker?, highlight: Boolean): String {
    var output = ""

    if (highlight) {
        output += "\u001B[33m"
    }
    output += when (cell) {
        Marker.X -> "  X  "
        Marker.O -> "  O  "
        else -> "     "
    }
    if (highlight) {
        output += "\u001B[0m"
    }

    if (index % boardSize != boardSize - 1) {
        output += "│"
    }

    return output
}

private fun Board.outputBoard(highlightLastMove: Boolean = true): String {
    val horizontalLine = List(boardSize) { "─────" }.joinToString("┼")

    var output = ""
    repeat(boardSize) { y ->
        repeat(boardSize) { x ->
            val index = y * boardSize + x
            val xValue = (stateX shr index) and 1
            val oValue = (stateO shr index) and 1
            val cell = when {
                xValue == 1L -> Marker.X
                oValue == 1L -> Marker.O
                else -> null
            }
            output += outputCell(index, cell, highlightLastMove && index == lastMove)
        }

        output += "\n"
        if (y != boardSize - 1) {
            output += horizontalLine + "\n"
        }
    }

    return output
}

/**
 * Prints the board to the console.
 *
 * @param highlightLastMove Whether to highlight the last move with a different color.
 */
fun Board.printBoard(highlightLastMove: Boolean = true) {
    println()
    println(outputBoard(highlightLastMove))
    println()
}
