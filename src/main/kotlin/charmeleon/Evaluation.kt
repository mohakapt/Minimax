package charmeleon

enum class NodeType {
    EXACT,
    LOWER_BOUND,
    UPPER_BOUND,
}

data class Evaluation(
    val score: Int,
    val type: NodeType,
    val depth: Int,
    val move: Int? = null,
)
