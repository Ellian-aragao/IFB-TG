package aragao.ellian.github.graph.domain.entity

import aragao.ellian.github.graph.domain.entity.representation.AbstractGraph

open class GraphSimpleReport(val totalVertexes: Int, val totalEdges: Int, val vertexesDegrees: List<Int>) {
    constructor(abstractGraph: AbstractGraph) : this(
        abstractGraph.getTotalVertex(),
        abstractGraph.getEdgeLenght(),
        abstractGraph.processVertexDegreesAndGet()
    )

    val lowerVertexDegreeValue get() = vertexesDegrees.min()

    val lowerVertexDegree get() = vertexesDegrees.indexOf(lowerVertexDegreeValue)

    val greaterVertexDegreeValue get() = vertexesDegrees.max()

    val greaterVertexDegree get() = vertexesDegrees.indexOf(greaterVertexDegreeValue)

    companion object {
        @JvmStatic
        fun of(abstractGraph: AbstractGraph) = GraphSimpleReport(abstractGraph)
    }
}
