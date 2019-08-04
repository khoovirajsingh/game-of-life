class World(private val row: Int = 0, private val column: Int = 0) {
    private val grid: Array<IntArray>

    init {
         grid = Array(row, {IntArray(column)})
    }

    fun isEmpty(): Boolean {
        return grid.isEmpty()
    }

    fun add(cell: Int) {
        grid[0][0] = 0
    }

}
