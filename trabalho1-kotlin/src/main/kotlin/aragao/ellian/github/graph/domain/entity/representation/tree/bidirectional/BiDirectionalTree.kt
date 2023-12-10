package aragao.ellian.github.graph.domain.entity.representation.tree.bidirectional

import aragao.ellian.github.graph.domain.entity.representation.AbstractGraph

class BiDirectionalTree(totalVertexes: Int): AbstractGraph(totalVertexes) {
    private val vertexesList = Array(totalVertexes) { BiDirectionalNode(it) }

    override fun insertEdge(vertex1: Int, vertex2: Int) {
        vertexesList[vertex1].addNextNode(vertexesList[vertex2])
        vertexesList[vertex2].addBackNode(vertexesList[vertex1])
    }

    override fun breadthFirstSearch(startVertex: Int): List<Int> {
        TODO("Not yet implemented")
    }

    override fun depthFirstSearch(startVertex: Int): List<Int> {
        TODO("Not yet implemented")
    }

    override fun processVertexDegree(): List<Int> {
        return vertexesList
            .map { it.getVertexDegree() }
            .toList()
    }
}