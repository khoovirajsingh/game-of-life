import org.amshove.kluent.`should be`
import org.junit.Test

class GameOfLifeSpec {
    @Test
    fun `next generation of cell is empty if grid is empty`() {
        val emptyGrid = Grid()
        val gameOfLife = GameOfLife(emptyGrid)
        gameOfLife.nextGeneration() `should be` emptyGrid
    }
}