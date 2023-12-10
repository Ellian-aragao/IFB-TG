package aragao.ellian.github.graph.entrypoint

import aragao.ellian.github.graph.entity.GraphData
import aragao.ellian.github.graph.entity.GraphReport
import aragao.ellian.github.graph.entity.representation.GraphAbstract
import aragao.ellian.github.graph.entity.representation.adjacency.AdjacentList
import aragao.ellian.github.graph.entity.representation.adjacency.AdjacentMatrix
import aragao.ellian.github.graph.entity.representation.tree.simple.Tree
import java.io.File
import java.util.*
import java.util.function.Function

class CliInterpreter(args: Array<String>) {
    private var graphData: GraphData? = null
    private val fileInputPath: String
    private var fileInput: File? = null
    private var fileOutput: File? = null

    init {
        if (args.size != 1)
            throw RuntimeException("Should has 1 argument, the input file graph")
        this.fileInputPath = args[0]
    }

    private fun getInstanceOfFileInput(): File {
        if (fileInput == null)
            fileInput = File(fileInputPath)

        return fileInput!!
    }

    private fun getInstanceOfFileOutput(): File {
        if (fileOutput == null)
            fileOutput = File("$fileInputPath-out.txt")

        return fileOutput!!
    }

    fun readAllFileData(): List<String> = getInstanceOfFileInput().readLines()

    private fun <T : GraphAbstract> readStreamAndGenerateFromFunction(function: Function<Int, T>): T {
        val (totalVertexes, edges) = readStreamAndGenerateGraphData()
        val graph = function.apply(totalVertexes)
        edges.forEach(graph::addEdge)
        return graph
    }

    @Synchronized
    fun readStreamAndGenerateGraphData(): GraphData {
        if (Objects.isNull(this.graphData)) {
            getInstanceOfFileInput().bufferedReader().use {
                val firstLine = it.readLine()
                val numberOfVertexes = Integer.parseInt(firstLine)
                val graphDataBuilder = GraphData.builder()
                    .totalVertexes(numberOfVertexes)
                var readedLine = it.readLine()
                do {
                    val vertexList = readedLine.split(" ")
                    val vertex1 = Integer.parseInt(vertexList[0]) - 1
                    val vertex2 = Integer.parseInt(vertexList[1]) - 1
                    graphDataBuilder.addEdge(vertex1, vertex2)
                    readedLine = it.readLine()
                } while (readedLine != null)
                this.graphData = graphDataBuilder.build()
            }
        }

        return graphData!!
    }

    fun readStreamAndGenerateAdjacentMatrix(): AdjacentMatrix {
        return readStreamAndGenerateFromFunction { totalVertexes: Int -> AdjacentMatrix(totalVertexes) }
    }

    fun readStreamAndGenerateAdjacentList(): AdjacentList {
        return readStreamAndGenerateFromFunction { totalVertexes: Int -> AdjacentList(totalVertexes) }
    }

    fun writeFileReportFromGraph(graphAbstract: GraphAbstract) = writeFileReportFromGraph(GraphReport.of(graphAbstract))

    fun writeFileReportFromGraph(graphReport: GraphReport) {
        getInstanceOfFileOutput().bufferedWriter().use {
            it.write("# n = ${graphReport.totalVertexes}")
            it.newLine()
            it.write("# m = ${graphReport.totalEdges}")
            it.newLine()
            graphReport.vertexesDegrees.forEachIndexed { vertex, degree ->
                it.write("${vertex + 1} $degree")
                it.newLine()
            }
            it.write("# greater degree vertex index = ${graphReport.greaterVertexDegree}")
            it.newLine()
            it.write("# greater degree vertex = ${graphReport.greaterVertexDegreeValue}")
            it.newLine()
            it.write("# lower degree vertex index = ${graphReport.lowerVertexDegree}")
            it.newLine()
            it.write("# lower degree vertex = ${graphReport.lowerVertexDegreeValue}")
            it.newLine()
        }
    }

    fun readStreamAndGenerateTreeSimpleNode(): Tree {
        return readStreamAndGenerateFromFunction { totalVertexes: Int -> Tree(totalVertexes) }
    }
}