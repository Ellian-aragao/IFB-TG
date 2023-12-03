package aragao.ellian.github.graph.entity

import java.util.*

class AdjacentList(private val totalVertex: Int) : GraphAbstract(totalVertex) {
    private val adjacentList = Array(totalVertex) { LinkedList<Int>() }

    override fun insertEdge(vertex1: Int, vertex2: Int) {
        adjacentList[vertex1].add(vertex2)
    }

    override fun processVertexDegree(): Array<Int> {
        return adjacentList
            .map { it.size / 2 }
            .toList()
            .toTypedArray()
    }
}