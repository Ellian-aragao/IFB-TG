package aragao.ellian.github.graph.entity

abstract class GraphAbstract(private val totalVertex: Int) {
    private var vertexDegree = Array(totalVertex) { 0 }
    private var edgeLenght = 0

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

    fun addEdge(vertex1: Int, vertex2: Int): GraphAbstract {
        validateVertexesToEdge(vertex1, vertex2)
        insertEdge(vertex1, vertex2)
        edgeLenght++
        return this
    }

    fun processAllVertexesDegrees(): GraphAbstract {
        vertexDegree = processVertexDegree()
        return this
    }

    fun getEdgeLenght() = edgeLenght

    fun getVertexDegress() = vertexDegree

    abstract fun processVertexDegree(): Array<Int>
    abstract fun insertEdge(vertex1: Int, vertex2: Int)
}