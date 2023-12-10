package aragao.ellian.github.graph.entity

import aragao.ellian.github.graph.domain.entity.representation.adjacency.AdjacentList
import aragao.ellian.github.graph.domain.entity.representation.AbstractGraph

class AdjacentListTest : AbstractGraphTest() {
    override fun provide(totalVertexes: Int): AbstractGraph {
        return AdjacentList(totalVertexes)
    }
}