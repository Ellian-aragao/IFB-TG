package aragao.ellian.github.graph.entity

class AdjacentListTest : GraphAbstractTest() {
    override fun provide(totalVertexes: Int): GraphAbstract {
        return AdjacentList(totalVertexes)
    }
}