package charmeleon

/**
 * Represents a marker on the board.
 */
enum class Marker {
    X, O;

    /**
     * Returns the opposite marker.
     *
     * @return `X` if the marker is `O`, `O` if the marker is `X`.
     */
    val opposite: Marker
        get() = if (this == X) O else X
    val asString: String
        get() = if (this == X) "X" else "O"
}
