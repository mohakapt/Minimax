data class Board(
    val boardSize: Int,
    val state: List<Marker?>,
    val turn: Marker,
    val lastMove: Int?,
) {
    val cellCount = boardSize * boardSize
}
