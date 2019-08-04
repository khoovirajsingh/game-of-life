class World {
    private var cell: Cell? = null

    fun isEmpty(): Boolean {
        return cell == null
    }

    fun add(cell: Cell) {
        this.cell = cell
    }

}
