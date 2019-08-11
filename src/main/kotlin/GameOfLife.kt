class GameOfLife(private val grid: Array<Array<Char>>) {
    private var maxColumn = 0
    private var maxRow = 0
    private val DEAD = '.'
    private val ALIVE = 'x'
    private var nextGen = createDefaultGrid()

    init {
        if (grid.isNotEmpty()) {
            maxRow = grid.size
            maxColumn = grid[0].size
            nextGen = createDefaultGrid()
        }
    }

    private fun createDefaultGrid() = Array(maxRow) { Array(maxColumn) { '.' } }

    fun nextGeneration(): Array<Array<Char>> {
        rows().forEach { row ->
            columns().forEach { column ->
                liveCellRemainsAlive(row, column)
                deadCellRegenerates(row, column)
            }
        }
        return nextGen
    }

    private fun rows(): IntRange = grid.indices

    private fun columns(): IntRange = grid[0].indices

    private fun liveCellRemainsAlive(row: Int, column: Int) {
        val cell = grid[row][column]
        if (isAlive(cell)) {
            when (liveNeighboursAround(row, column)) {
                2, 3 -> cellIsAlive(row, column)
            }
        }
    }

    private fun deadCellRegenerates(row: Int, column: Int) {
        val cell = grid[row][column]
        if (isDead(cell)) {
            when (liveNeighboursAround(row, column)) {
                3 -> cellIsAlive(row, column)
            }
        }
    }

    fun liveNeighboursAround(row: Int, column: Int) =
        numOfAliveCells(row - 1, column - 1) +
                numOfAliveCells(row - 1, column) +
                numOfAliveCells(row - 1, column + 1) +
                numOfAliveCells(row, column - 1) +
                numOfAliveCells(row, column + 1) +
                numOfAliveCells(row + 1, column - 1) +
                numOfAliveCells(row + 1, column) +
                numOfAliveCells(row + 1, column + 1)

    private fun numOfAliveCells(row: Int, column: Int) = when {
        isWithinGrid(row, column) && isAlive(grid[row][column]) -> 1
        else -> 0
    }

    private fun isWithinGrid(row: Int, column: Int) = isWithinRowBoundary(row) &&
            isWithinColumnBoundary(column)

    private fun isWithinColumnBoundary(column: Int) = column in 0 until maxColumn

    private fun isWithinRowBoundary(row: Int) = row in 0 until maxRow

    private fun cellIsAlive(row: Int, column: Int) {
        nextGen[row][column] = ALIVE
    }

    private fun isAlive(cell: Char) = cell == ALIVE

    private fun isDead(cell: Char) = cell == DEAD
}
