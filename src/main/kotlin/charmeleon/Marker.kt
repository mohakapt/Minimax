package charmeleon

/**
 * Represents a marker on the board.
 *
 * @see Board
 */
enum class Marker {
    X, O;

    /**
     * Retrieves the opposite marker.
     *
     * @return `X` if the marker is `O`, `O` if the marker is `X`.
     */
    val opposite: Marker
        get() = if (this == X) O else X

    /**
     * Retrieves the marker as a string.
     *
     * @return `"X"` if the marker is `X`, `"O"` if the marker is `O`.
     */
    val asString: String
        get() = if (this == X) "X" else "O"
}
