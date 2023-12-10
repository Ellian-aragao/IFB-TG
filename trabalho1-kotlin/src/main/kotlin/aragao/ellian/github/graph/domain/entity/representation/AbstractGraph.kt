package aragao.ellian.github.graph.domain.entity.representation

import aragao.ellian.github.graph.domain.entity.GraphEdge

abstract class AbstractGraph(private val totalVertex: Int) {
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

    fun addEdge(edge: GraphEdge): AbstractGraph {
        return addEdge(edge.vertex1, edge.vertex2)
    }

    fun addEdge(vertex1: Int, vertex2: Int): AbstractGraph {
        validateVertexesToEdge(vertex1, vertex2)
        insertEdge(vertex1, vertex2)
        edgeLenght++
        return this
    }

    fun processAllVertexesDegrees(): AbstractGraph {
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

    inline fun vertexDegressIsComputed(action: (AbstractGraph) -> Unit) {
        action(this)
    }

    fun getVertexDegreesIfComputed() = vertexDegree.ifEmpty { null }

    protected abstract fun processVertexDegree(): List<Int>
    protected abstract fun insertEdge(vertex1: Int, vertex2: Int)
    abstract fun breadthFirstSearch(startVertex: Int = 0): List<Int>
    abstract fun depthFirstSearch(startVertex: Int = 0): List<Int>
}