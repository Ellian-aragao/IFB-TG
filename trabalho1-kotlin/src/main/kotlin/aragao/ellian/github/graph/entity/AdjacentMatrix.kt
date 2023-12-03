package aragao.ellian.github.graph.entity

class AdjacentMatrix(private val totalVertex: Int, private var preCompute: Boolean = false) : GraphAbstract(totalVertex) {
    private val matrixAdjacent = Array(totalVertex) { IntArray(totalVertex) }

    override fun insertEdge(vertex1: Int, vertex2: Int) {
        matrixAdjacent[vertex1][vertex2]++
        matrixAdjacent[vertex2][vertex1]++
        if (preCompute)
            processBiVertexDegree(vertex1, vertex2)
    }

    private fun processBiVertexDegree(vertex1: Int, vertex2: Int) {
        getVertexDegress()[vertex1] = processVertexDegree(matrixAdjacent[vertex1])
        getVertexDegress()[vertex2] = processVertexDegree(matrixAdjacent[vertex2])
    }

    private fun processVertexDegree(vertexLine: IntArray): Int = vertexLine.reduce(Int::plus)

    override fun processVertexDegree(): Array<Int> {
        return matrixAdjacent
            .map(this::processVertexDegree)
            .toTypedArray()
    }

    override fun toString(): String {
        return "MatrixAdjacent(${System.lineSeparator()}" +
                " totalVertex = $totalVertex, ${System.lineSeparator()}" +
                " preCompute = $preCompute, ${System.lineSeparator()}" +
                " edgeLenght = ${getEdgeLenght()},${System.lineSeparator()}" +
                " vertexDegree: ${System.lineSeparator()}  ${getVertexDegress().contentToString()}${System.lineSeparator()}" +
                " matrixAdjacent: ${System.lineSeparator()}  " +
                matrixAdjacent.joinToString("  ") { "[${it.joinToString()}]${System.lineSeparator()}" } +
                ")"
    }
}
