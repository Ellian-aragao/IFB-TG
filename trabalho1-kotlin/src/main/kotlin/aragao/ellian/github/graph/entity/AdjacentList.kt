package aragao.ellian.github.graph.entity

import java.util.*

class AdjacentList(totalVertex: Int) : GraphAbstract(totalVertex) {
    private val adjacentList = Array(totalVertex) { LinkedList<Int>() }

    override fun insertEdge(vertex1: Int, vertex2: Int) {
        adjacentList[vertex1].add(vertex2)
        adjacentList[vertex2].add(vertex1)
    }

    override fun processVertexDegree(): List<Int> {
        return adjacentList
            .map { it.size }
            .toList()
    }
}