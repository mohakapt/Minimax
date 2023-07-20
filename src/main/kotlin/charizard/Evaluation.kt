package charizard

/**
 * The type of node in the search tree, used to determine the type of the evaluation.
 * [EXACT]: The evaluation is exact, meaning it is the actual score of the board.
 * [LOWER_BOUND]: The evaluation is a lower bound of the actual score, meaning it is at least the actual score.
 * [UPPER_BOUND]: The evaluation is an upper bound of the actual score, meaning it is at most the actual score.
 *
 * @see Evaluation
 */
enum class NodeType {
    EXACT,
    LOWER_BOUND,
    UPPER_BOUND,
}

/**
 * The evaluation of a board.
 *
 * @param score The score of the board.
 * @param type The type of the node in the search tree.
 * @param depth The depth of the current move, used to give more weight to faster wins.
 * @param move The best move to get the given score, if it exists.
 * @see minimax
 * @see NodeType
 */
data class Evaluation(
    val score: Int,
    val type: NodeType,
    val depth: Int,
    val move: Int? = null,
)
