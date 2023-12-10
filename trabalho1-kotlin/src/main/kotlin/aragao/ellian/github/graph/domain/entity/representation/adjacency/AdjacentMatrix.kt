package aragao.ellian.github.graph.domain.entity.representation.adjacency

import aragao.ellian.github.graph.domain.entity.representation.AbstractGraph

class AdjacentMatrix(private val totalVertex: Int) : AbstractGraph(totalVertex) {
    private val adjacencyMatrix = Array(totalVertex) { BooleanArray(totalVertex) }

    override fun insertEdge(vertex1: Int, vertex2: Int) {
        adjacencyMatrix[vertex1][vertex2] = true
        adjacencyMatrix[vertex2][vertex1] = true
    }

    override fun processVertexDegree(): List<Int> {
        return adjacencyMatrix
            .map { lineMatrix -> lineMatrix.map(this::booleanToInt) }
            .map { it.reduce(Int::plus) }
            .toList()
    }

    private fun booleanToInt(bool: Boolean): Int = if (bool) 1 else 0

    override fun depthFirstSearch(startVertex: Int): List<Int> {
        val visited = mutableSetOf<Int>()
        val result = mutableListOf<Int>()
        fun depthFirstSearch(vertex: Int) {
            visited.add(vertex)
            result.add(vertex)
            adjacencyMatrix[vertex].forEachIndexed { index, item ->
                if (item and (index !in visited)) {
                    depthFirstSearch(index)
                }
            }
        }
        depthFirstSearch(startVertex)
        return result
    }

    override fun breadthFirstSearch(startVertex: Int): List<Int> {
        val totalVertex = getTotalVertex()
        val visited = BooleanArray(totalVertex)
        val result = mutableListOf<Int>()
        val queue = mutableListOf<Int>()

        visited[startVertex] = true
        queue.add(startVertex)

        while (queue.isNotEmpty()) {
            val currentVertex = queue.removeFirst()
            result.add(currentVertex)

            for (adjVertex in 0..<totalVertex) {
                if (adjacencyMatrix[currentVertex][adjVertex] && !visited[adjVertex]) {
                    visited[adjVertex] = true
                    queue.add(adjVertex)
                }
            }
        }

        return result
    }

    override fun toString(): String {
        return "MatrixAdjacent(${System.lineSeparator()}" +
                " totalVertex = $totalVertex, ${System.lineSeparator()}" +
                " edgeLenght = ${getEdgeLenght()},${System.lineSeparator()}" +
                " vertexDegree: ${System.lineSeparator()}  ${processVertexDegreesAndGet().joinToString(",")}${System.lineSeparator()}" +
                " matrixAdjacent: ${System.lineSeparator()}  " +
                adjacencyMatrix.joinToString("  ") { lineMatrix ->
                    "[${
                        lineMatrix.map(this::booleanToInt).joinToString()
                    }]${System.lineSeparator()}"
                } +
                ")"
    }
}
