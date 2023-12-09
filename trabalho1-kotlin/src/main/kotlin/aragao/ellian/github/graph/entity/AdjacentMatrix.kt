package aragao.ellian.github.graph.entity

class AdjacentMatrix(private val totalVertex: Int) : GraphAbstract(totalVertex) {
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

    private fun depthFirstSearch(vertex: Int, visited: BooleanArray, result: MutableList<Int>) {
        visited[vertex] = true
        result.add(vertex)
        adjacencyMatrix[vertex].forEachIndexed { index, item ->
            if (item and !visited[index]) {
                depthFirstSearch(index, visited, result)
            }
        }
    }

    fun depthFirstSearch(startingVertex: Int = 0): List<Int> {
        val visited = BooleanArray(getTotalVertex())
        val result = mutableListOf<Int>()
        depthFirstSearch(startingVertex, visited, result)
        return result
    }

    fun breadthFirstSearch(startVertex: Int = 0): List<Int> {
        val totalVertex = getTotalVertex()
        val visited = BooleanArray(totalVertex)
        val result = mutableListOf<Int>()
        val queue = mutableListOf<Int>()

        visited[startVertex] = true
        queue.add(startVertex)

        while (queue.isNotEmpty()) {
            val currentVertex = queue.removeAt(0)
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
