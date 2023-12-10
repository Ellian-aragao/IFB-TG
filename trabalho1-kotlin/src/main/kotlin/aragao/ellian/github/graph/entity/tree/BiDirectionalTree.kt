package aragao.ellian.github.graph.entity.tree

import aragao.ellian.github.graph.entity.GraphAbstract

class BiDirectionalTree(totalVertexes: Int): GraphAbstract(totalVertexes) {
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