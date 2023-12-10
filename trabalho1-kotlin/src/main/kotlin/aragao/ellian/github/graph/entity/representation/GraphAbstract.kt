package aragao.ellian.github.graph.entity.representation

import aragao.ellian.github.graph.entity.GraphEdge

abstract class GraphAbstract(private val totalVertex: Int) {
    private var vertexDegree = listOf<Int>()
    private var edgeLenght = 0

    private fun isValidVertex(vertex: Int): Boolean = vertex in 0..<this.totalVertex

    private fun validateVertexesToEdge(vertex1: Int, vertex2: Int) {
        val vertexesAreValid = isValidVertex(vertex1) and isValidVertex(vertex2)
        if (!vertexesAreValid) {
            val message =
                "Bounds of vertex shound be major than 0 and minour than ${this.totalVertex}, the entry value was (${vertex1}, ${vertex2})"
            throw RuntimeException(message)
        }
    }

    fun addEdge(edge: GraphEdge): GraphAbstract {
        return addEdge(edge.vertex1, edge.vertex2)
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

    fun getTotalVertex() = totalVertex
    fun getEdgeLenght() = edgeLenght

    fun processVertexDegreesAndGet(): List<Int> {
        if (!vertexDegressIsComputed()) processAllVertexesDegrees()
        return vertexDegree
    }

    fun vertexDegressIsComputed() = vertexDegree.isNotEmpty()

    inline fun vertexDegressIsComputed(action: (GraphAbstract) -> Unit) {
        action(this)
    }

    fun getVertexDegreesIfComputed() = vertexDegree.ifEmpty { null }

    protected abstract fun processVertexDegree(): List<Int>
    protected abstract fun insertEdge(vertex1: Int, vertex2: Int)
    abstract fun breadthFirstSearch(startVertex: Int = 0): List<Int>
    abstract fun depthFirstSearch(startVertex: Int = 0): List<Int>
}