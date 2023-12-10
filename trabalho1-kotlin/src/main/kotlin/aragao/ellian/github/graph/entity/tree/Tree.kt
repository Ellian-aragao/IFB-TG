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

    override fun depthFirstSearch(startVertex: Int): List<Int> {
        val visited = mutableSetOf<Int>()
        val result = mutableListOf<Int>()
        fun depthFirstSearch(vertex: Int) {
            visited.add(vertex)
            result.add(vertex)
            vertexesList[vertex].forEachEdgesNodes {
                val neighborVertex = it.vertex
                if (neighborVertex !in visited) {
                    depthFirstSearch(neighborVertex)
                }
            }
        }
        depthFirstSearch(startVertex)
        return result
    }

    override fun breadthFirstSearch(startVertex: Int): List<Int> {
        val visited = BooleanArray(getTotalVertex())
        val result = mutableListOf<Int>()
        val queue = ArrayDeque<Int>()

        visited[startVertex] = true
        queue.add(startVertex)

        while (queue.isNotEmpty()) {
            val currentVertex = queue.removeFirst()
            result.add(currentVertex)
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

    fun findConnectedComponents(): List<List<Int>> {
        val visited = BooleanArray(getTotalVertex())
        return vertexesList.filter { !visited[it.vertex] }
            .map(Node::vertex)
            .map(this::depthFirstSearch)
    }
}