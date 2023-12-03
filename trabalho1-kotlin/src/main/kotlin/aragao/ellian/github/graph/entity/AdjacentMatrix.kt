package aragao.ellian.github.graph.entity

class AdjacentMatrix(private val totalVertex: Int) : GraphAbstract(totalVertex) {
    private val matrixAdjacent = Array(totalVertex) { IntArray(totalVertex) }

    override fun insertEdge(vertex1: Int, vertex2: Int) {
        matrixAdjacent[vertex1][vertex2]++
        matrixAdjacent[vertex2][vertex1]++
    }

    override fun processVertexDegree(): List<Int> {
        return matrixAdjacent
            .map { it.reduce(Int::plus) }
            .toList()
    }

    override fun toString(): String {
        return "MatrixAdjacent(${System.lineSeparator()}" +
                " totalVertex = $totalVertex, ${System.lineSeparator()}" +
                " edgeLenght = ${getEdgeLenght()},${System.lineSeparator()}" +
                " vertexDegree: ${System.lineSeparator()}  ${processVertexDegreesAndGet().joinToString(",")}${System.lineSeparator()}" +
                " matrixAdjacent: ${System.lineSeparator()}  " +
                matrixAdjacent.joinToString("  ") { "[${it.joinToString()}]${System.lineSeparator()}" } +
                ")"
    }
}
