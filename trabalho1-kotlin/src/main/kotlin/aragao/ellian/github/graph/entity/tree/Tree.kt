package aragao.ellian.github.graph.entity.tree

import aragao.ellian.github.graph.entity.GraphAbstract

class Tree(totalVertexes: Int): GraphAbstract(totalVertexes) {
    private val vertexesList = Array(totalVertexes) { Node(it) }

    override fun insertEdge(vertex1: Int, vertex2: Int) {
        vertexesList[vertex1].addEdge(vertexesList[vertex2])
        vertexesList[vertex2].addEdge(vertexesList[vertex1])
    }

    override fun processVertexDegree(): List<Int> {
        return vertexesList
            .map { it.getVertexDegree() }
            .toList()
    }


}