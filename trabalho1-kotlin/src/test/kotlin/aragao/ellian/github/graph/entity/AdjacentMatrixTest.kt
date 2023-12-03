package aragao.ellian.github.graph.entity

class AdjacentMatrixTest : GraphAbstractTest() {
    override fun provide(totalVertexes: Int): GraphAbstract {
        return AdjacentMatrix(totalVertexes)
    }
}