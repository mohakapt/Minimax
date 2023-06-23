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
            val cell = state[index]
            val highlight = highlightLastMove && index == lastMove
            output += outputCell(index, cell, highlight)
        }
        output += "\n"
        if (y != boardSize - 1) {
            output += horizontalLine + "\n"
        }
    }

    return output
}

fun Board.printBoard(highlightLastMove: Boolean) {
    println()
    println(outputBoard(highlightLastMove))
    println()
}
