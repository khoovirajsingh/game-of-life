import org.amshove.kluent.`should be`
import org.junit.Test

class GameOfLife_spec {
    @Test
    fun `next generation is an empty grid if grid is empty`() {
        val grid = arrayOf<Array<Char>>()

        val gameOfLife = GameOfLife(grid)

        gameOfLife.nextGeneration() contentDeepEquals grid `should be` true
    }

    @Test
    fun `a live cell should die if grid has only 1 live cell`() {
        val grid = arrayOf(arrayOf('x'))
        val gameOfLife = GameOfLife(grid)

        val nextGenGrid = gameOfLife.nextGeneration()

        grid contentDeepEquals nextGenGrid `should be` false
    }

    @Test
    fun `a dead cell should stay if grid has only 1 dead cell`() {
        val grid = arrayOf(arrayOf('.'))
        val gameOfLife = GameOfLife(grid)

        val nextGenGrid = gameOfLife.nextGeneration()

        grid contentDeepEquals nextGenGrid `should be` true
    }


    @Test
    fun `count alive neighbours in a 1x1 grid`() {
        val grid = arrayOf(arrayOf('.'))
        val gameOfLife = GameOfLife(grid)

        val aliveNeighbours = gameOfLife.liveNeighboursAround(row = 0, column = 0)

        aliveNeighbours `should be` 0
    }

    @Test
    fun `count alive neighbours in a 2x2 grid`() {
        val grid = arrayOf(
            arrayOf('x', '.'),
            arrayOf('x', '.'))
        val gameOfLife = GameOfLife(grid)

        var aliveNeighbours = gameOfLife.liveNeighboursAround(row = 1, column = 1)
        aliveNeighbours `should be` 2

        aliveNeighbours = gameOfLife.liveNeighboursAround(row = 1, column = 0)
        aliveNeighbours `should be` 1

        aliveNeighbours = gameOfLife.liveNeighboursAround(row = 0, column = 0)
        aliveNeighbours `should be` 1

        aliveNeighbours = gameOfLife.liveNeighboursAround(row = 0, column = 1)
        aliveNeighbours `should be` 2
    }

    @Test
    fun `count alive neighbours in a 3x3 grid`() {
        val grid = arrayOf(
            arrayOf('x', '.', 'x'),
            arrayOf('.', '.', 'x'),
            arrayOf('x', '.', '.'))
        val gameOfLife = GameOfLife(grid)

        val aliveNeighbours = gameOfLife.liveNeighboursAround(row = 1, column = 1)
        aliveNeighbours `should be` 4
    }

    @Test
    fun `any live cell with fewer than two live neighbours dies, as if caused by underpopulation`() {
        val currentGrid = arrayOf(arrayOf('x', 'x', 'x'))
        val nextGenGrid = arrayOf(arrayOf('.', 'x', '.'))
        val gameOfLife = GameOfLife(currentGrid)

        gameOfLife.nextGeneration() contentDeepEquals nextGenGrid `should be` true
    }

    @Test
    fun `any live cell with more than three live neighbours dies, as if by overcrowding`() {
        val currentGrid = arrayOf(
            arrayOf('x', 'x', 'x'),
            arrayOf('x', 'x', '.'))

        val nextGenGrid = arrayOf(
            arrayOf('x', '.', 'x'),
            arrayOf('x', '.', 'x'))

        val gameOfLife = GameOfLife(currentGrid)

        gameOfLife.nextGeneration() contentDeepEquals nextGenGrid `should be` true
    }

    @Test
    fun `any live cell with two or three live neighbours lives on to the next generation`() {
        val currentGrid = arrayOf(
            arrayOf('x', '.', '.'),
            arrayOf('.', 'x', 'x'),
            arrayOf('x', 'x', '.'))

        val nextGenGrid = arrayOf(
            arrayOf('.', 'x', '.'),
            arrayOf('.', '.', 'x'),
            arrayOf('x', 'x', 'x'))

        val gameOfLife = GameOfLife(currentGrid)

        gameOfLife.nextGeneration() contentDeepEquals nextGenGrid `should be` true
    }
}