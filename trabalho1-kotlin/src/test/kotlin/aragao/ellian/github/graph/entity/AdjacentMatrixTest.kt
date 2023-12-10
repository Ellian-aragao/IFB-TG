package aragao.ellian.github.graph.entity

import aragao.ellian.github.graph.entity.representation.adjacency.AdjacentMatrix
import aragao.ellian.github.graph.entity.representation.GraphAbstract

class AdjacentMatrixTest : GraphAbstractTest() {
    override fun provide(totalVertexes: Int): GraphAbstract {
        return AdjacentMatrix(totalVertexes)
    }
}