package aragao.ellian.github.graph.entity.report

import aragao.ellian.github.graph.entity.GraphAbstract

data class GraphReport(val totalVertexes: Int, val totalEdges: Int, val vertexesDegrees: List<Int>) {

    constructor(graphAbstract: GraphAbstract) : this(
        graphAbstract.getTotalVertex(),
        graphAbstract.getEdgeLenght(),
        graphAbstract.processVertexDegreesAndGet()
    )

    val lowerVertexDegreeValue get() = vertexesDegrees.min()

    val lowerVertexDegree get() = vertexesDegrees.indexOf(lowerVertexDegreeValue)

    val greaterVertexDegreeValue get() = vertexesDegrees.max()

    val greaterVertexDegree get() = vertexesDegrees.indexOf(greaterVertexDegreeValue)

    companion object {
        @JvmStatic
        fun of(graphAbstract: GraphAbstract) = GraphReport(graphAbstract)
    }
}
