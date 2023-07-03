package charmander

/**
 * Represents a marker on the board.
 */
enum class Marker {
    X, O;

    val opposite: Marker
        get() = if (this == X) O else X
}
