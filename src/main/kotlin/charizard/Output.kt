package charizard

private fun Board.outputCell(index: Int, highlight: Boolean): String {
    var output = ""
    if (highlight) output += "\u001B[33m"

    val marker = markerAt(index)
    output += "  ${marker?.asString ?: " "}  "

    if (highlight) output += "\u001B[0m"
    if (index % boardSize != boardSize - 1) output += "│"

    return output
}

private fun Board.outputBoard(highlightLastMove: Boolean = true): String {
    val horizontalLine = List(boardSize) { "─────" }.joinToString("┼")

    var output = ""
    repeat(boardSize) { y ->
        repeat(boardSize) { x ->
            val index = indexOf(x, y)
            output += outputCell(index, highlightLastMove && index == lastMove)
        }

        output += "\n"
        if (y != boardSize - 1) output += horizontalLine + "\n"
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
