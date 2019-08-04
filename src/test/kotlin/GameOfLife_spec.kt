import org.amshove.kluent.`should be`
import org.junit.Test

class GameOfLife_spec {
    @Test
    fun `next generation of world is empty if world is empty`() {
        val emptyWorld = World()
        val gameOfLife = GameOfLife(emptyWorld)
        gameOfLife.nextGeneration() `should be` emptyWorld
    }

    @Test
    fun `next generation of world contains a dead cell if world started with a dead cell`() {

    }
}