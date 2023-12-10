package aragao.ellian.github.graph.entity

import aragao.ellian.github.graph.entity.representation.adjacency.AdjacentList
import aragao.ellian.github.graph.entity.representation.GraphAbstract

class AdjacentListTest : GraphAbstractTest() {
    override fun provide(totalVertexes: Int): GraphAbstract {
        return AdjacentList(totalVertexes)
    }
}