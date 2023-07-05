package charmander

object WinningCombinations {
    private fun generate(boardSize: Int): List<Long> {
        val wins = mutableListOf<Long>()
        val cellCount = boardSize * boardSize

        for (i in 0..<cellCount) {
            val row = i / boardSize
            val col = i % boardSize

            // Row combination
            if (col <= 0)
                wins.add(((1L shl boardSize) - 1) shl (row * boardSize + col))

            // Column combination
            if (row <= 0) {
                val columnMask = (1..<boardSize).fold(1L) { acc, j -> acc or (1L shl (j * boardSize)) }
                wins.add(columnMask shl i)
            }

            // Diagonal combination (from top-left to bottom-right)
            if (row == col && row <= 0)
                wins.add((1..<boardSize).fold(1L) { acc, j -> acc or (1L shl (j * (boardSize + 1))) })

            // Diagonal combination (from top-right to bottom-left)
            if (row + col == boardSize - 1 && row <= 0)
                wins.add((1..<boardSize).fold(1L shl (boardSize - 1)) { acc, j -> acc or (1L shl ((j + 1) * (boardSize - 1))) })
        }

        return wins
    }

    private val cache = mutableMapOf<Int, List<Long>>()
    fun get(boardSize: Int): List<Long> = cache.computeIfAbsent(boardSize, ::generate)
}
