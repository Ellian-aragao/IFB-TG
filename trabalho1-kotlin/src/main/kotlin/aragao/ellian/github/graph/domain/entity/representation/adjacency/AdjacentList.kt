package aragao.ellian.github.graph.domain.entity.representation.adjacency

import aragao.ellian.github.graph.domain.entity.GraphData
import aragao.ellian.github.graph.domain.entity.representation.AbstractGraph
import java.util.*

class AdjacentList(totalVertex: Int) : AbstractGraph(totalVertex) {

    constructor(graphData: GraphData) : this(graphData.totalVertexes) {
        graphData.edges.forEach(this::addEdge)
    }

    private val adjacencyList = Array(totalVertex) { LinkedList<Int>() }

    override fun insertEdge(vertex1: Int, vertex2: Int) {
        adjacencyList[vertex1].add(vertex2)
        adjacencyList[vertex2].add(vertex1)
    }

    override fun depthFirstSearch(startVertex: Int): List<Int> {
        val visited = mutableSetOf<Int>()
        val result = mutableListOf<Int>()

        fun depthFirstSearch(vertex: Int) {
            visited.add(vertex)
            result.add(vertex)
            adjacencyList[vertex].forEach { neighbor ->
                if (neighbor !in visited) {
                    depthFirstSearch(neighbor)
                }
            }
        }

        depthFirstSearch(startVertex)
        return result
    }

    override fun breadthFirstSearch(startVertex: Int): List<Int> {
        val visited = mutableSetOf<Int>()
        val result = mutableListOf<Int>()
        val queue = mutableListOf<Int>()

        visited.add(startVertex)
        queue.add(startVertex)

        while (queue.isNotEmpty()) {
            val currentVertex = queue.removeAt(0)
            result.add(currentVertex)

            adjacencyList[currentVertex].forEach { neighbor ->
                if (neighbor !in visited) {
                    visited.add(neighbor)
                    queue.add(neighbor)
                }
            }
        }

        return result
    }

    override fun processVertexDegree(): List<Int> {
        return adjacencyList
            .map { it.size }
            .toList()
    }
}