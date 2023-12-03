package aragao.ellian.github.graph.entity.tree

import aragao.ellian.github.graph.entity.GraphAbstract

class Tree(totalVertexes: Int) : GraphAbstract(totalVertexes) {
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

    fun depthFirstSearch(startingVertex: Int = 0): List<Int> {
        val visited = BooleanArray(getTotalVertex())
        val result = mutableListOf<Int>()
        depthFirstSearch(startingVertex, visited, result)
        return result
    }

    private fun depthFirstSearch(vertex: Int, visited: BooleanArray, result: MutableList<Int>) {
        visited[vertex] = true
        result.add(vertex + 1)
        vertexesList[vertex].forEachEdgesNodes {
            val neighborVertex = it.vertex
            if (!visited[neighborVertex]) {
                depthFirstSearch(neighborVertex, visited, result)
            }
        }
    }

    fun breadthFirstSearch(startingVertex: Int = 0): List<Int> {
        val visited = BooleanArray(getTotalVertex())
        val result = mutableListOf<Int>()
        val queue = ArrayDeque<Int>()

        visited[startingVertex] = true
        queue.add(startingVertex)

        while (queue.isNotEmpty()) {
            val currentVertex = queue.removeFirst()
            result.add(currentVertex + 1)
            vertexesList[currentVertex].forEachEdgesNodes {
                val neighborVertex = it.vertex
                if (!visited[neighborVertex]) {
                    visited[neighborVertex] = true
                    queue.add(neighborVertex)
                }
            }
        }
        return result
    }
}