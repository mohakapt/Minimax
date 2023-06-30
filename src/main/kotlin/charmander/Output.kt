package charmander

private fun Board.outputCell(index: Int, cell: Marker?, highlight: Boolean): String {
    TODO("I will have a look at this later")
}

private fun Board.outputBoard(highlightLastMove: Boolean = true): String {
    TODO("I will have a look at this later")
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
