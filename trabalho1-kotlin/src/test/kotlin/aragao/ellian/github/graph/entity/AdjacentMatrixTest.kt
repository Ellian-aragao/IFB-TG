package aragao.ellian.github.graph.entity

import aragao.ellian.github.graph.domain.entity.representation.adjacency.AdjacentMatrix
import aragao.ellian.github.graph.domain.entity.representation.AbstractGraph

class AdjacentMatrixTest : AbstractGraphTest() {
    override fun provide(totalVertexes: Int): AbstractGraph {
        return AdjacentMatrix(totalVertexes)
    }
}