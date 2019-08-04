import org.amshove.kluent.`should be`
import org.junit.Test

class World_spec {
    @Test
    fun `new world is empty`() {
        val world = World()
        world.isEmpty() `should be` true
    }

    @Test
    fun `world is not empty if a cell is added`() {
        val world = World(1, 1)
        val cell = 0

        world.add(cell)

        world.isEmpty() `should be` false
    }
}