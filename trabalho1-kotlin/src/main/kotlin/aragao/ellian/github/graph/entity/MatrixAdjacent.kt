package aragao.ellian.github.graph.entity

class MatrixAdjacent(private val totalVertex: Int, private var preCompute: Boolean = false) {
    private val matrixAdjacent = Array(totalVertex) { IntArray(totalVertex) }
    private var vertexDegree = Array(totalVertex) { 0 }
    private var edgeLenght = 0L

    private fun validateBoundsVertex(vertex: Int): Boolean = 0 <= vertex && vertex < this.totalVertex

    private fun validateVertexesToEdge(vertex1: Int, vertex2: Int) {
        val isValidVertex1 = validateBoundsVertex(vertex1)
        val isValidVertex2 = validateBoundsVertex(vertex2)
        if (!isValidVertex1 or !isValidVertex2) {
            val message =
                "Bounds of vertex shound be major than 0 and minour than ${this.totalVertex}, the entry value was (${vertex1}, ${vertex2})"
            throw RuntimeException(message)
        }
    }

    fun addEdge(vertex1: Int, vertex2: Int): MatrixAdjacent {
        validateVertexesToEdge(vertex1, vertex2)
        matrixAdjacent[vertex1][vertex2]++
        matrixAdjacent[vertex2][vertex1]++
        edgeLenght++
        if (preCompute)
            processBiVertexDegree(vertex1, vertex2)
        return this
    }

    private fun processBiVertexDegree(vertex1: Int, vertex2: Int) {
        vertexDegree[vertex1] = processVertexDegree(matrixAdjacent[vertex1])
        vertexDegree[vertex2] = processVertexDegree(matrixAdjacent[vertex2])
    }

    private fun processVertexDegree(vertexLine: IntArray): Int = vertexLine.reduce(Int::plus)

    fun processAllVertexesDegrees(): MatrixAdjacent {
        vertexDegree = matrixAdjacent
            .map(this::processVertexDegree)
            .toTypedArray()
        return this
    }

    override fun toString(): String {
        return "MatrixAdjacent(${System.lineSeparator()}" +
                " totalVertex = $totalVertex, ${System.lineSeparator()}" +
                " preCompute = $preCompute, ${System.lineSeparator()}" +
                " edgeLenght = $edgeLenght,${System.lineSeparator()}" +
                " vertexDegree: ${System.lineSeparator()}  ${vertexDegree.contentToString()}${System.lineSeparator()}" +
                " matrixAdjacent: ${System.lineSeparator()}  " +
                matrixAdjacent.joinToString("  ") { "[${it.joinToString()}]${System.lineSeparator()}" } +
                ")"
    }

    fun getVertexDegress() = vertexDegree
    fun getEdgeLenght() = edgeLenght
}
