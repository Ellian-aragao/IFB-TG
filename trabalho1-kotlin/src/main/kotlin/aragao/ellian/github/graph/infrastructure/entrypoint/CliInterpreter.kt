package aragao.ellian.github.graph.infrastructure.entrypoint

import aragao.ellian.github.graph.domain.entity.GraphData
import aragao.ellian.github.graph.domain.entity.GraphSimpleReport
import aragao.ellian.github.graph.domain.entity.GraphTimeReport
import aragao.ellian.github.graph.domain.entity.representation.AbstractGraph
import aragao.ellian.github.graph.domain.entity.representation.adjacency.AdjacentList
import aragao.ellian.github.graph.domain.entity.representation.adjacency.AdjacentMatrix
import aragao.ellian.github.graph.domain.entity.representation.tree.simple.Tree
import java.io.BufferedWriter
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

    private fun <T : AbstractGraph> readStreamAndGenerateFromFunction(function: Function<Int, T>): T {
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

    fun writeFileReportFromGraph(graphData: GraphData) {
        getInstanceOfFileOutput().bufferedWriter().use { bufferedWriter ->
            val adjacentList = AdjacentList(graphData)
            val adjacentMatrix = AdjacentMatrix(graphData)

            val timeReports = listOf(adjacentList, adjacentMatrix)
                .map(GraphTimeReport::of)

            writeSimpleReport(bufferedWriter, timeReports.first())

            timeReports
                .map { graphReport ->
                    val (bfsIndex, graphBfsSearchTime) = graphReport.indexAndExecutionTimeBreadFirstSearchGraphWorstCase()
                    bufferedWriter.write("${graphReport.clazz} vertex ${bfsIndex + 1} execution BFS worst case: $graphBfsSearchTime${System.lineSeparator()}")
                    val (dfsIndex, graphDfsSearchTime) = graphReport.indexAndExecutionTimeDepthFirstSearchGraphWorstCase()
                    bufferedWriter.write("${graphReport.clazz} vertex ${dfsIndex + 1} execution DFS worst case: $graphDfsSearchTime${System.lineSeparator()}")
                }

        }
    }

    private fun writeSimpleReport(
        bufferedWriter: BufferedWriter,
        graphSimpleReport: GraphSimpleReport
    ) {
        bufferedWriter.write("# n = ${graphSimpleReport.totalVertexes}")
        bufferedWriter.newLine()
        bufferedWriter.write("# m = ${graphSimpleReport.totalEdges}")
        bufferedWriter.newLine()
        graphSimpleReport.vertexesDegrees.forEachIndexed { vertex, degree ->
            bufferedWriter.write("${vertex + 1} $degree")
            bufferedWriter.newLine()
        }
        bufferedWriter.write("# greater degree vertex index = ${graphSimpleReport.greaterVertexDegree}")
        bufferedWriter.newLine()
        bufferedWriter.write("# greater degree vertex = ${graphSimpleReport.greaterVertexDegreeValue}")
        bufferedWriter.newLine()
        bufferedWriter.write("# lower degree vertex index = ${graphSimpleReport.lowerVertexDegree}")
        bufferedWriter.newLine()
        bufferedWriter.write("# lower degree vertex = ${graphSimpleReport.lowerVertexDegreeValue}")
        bufferedWriter.newLine()
    }

    fun readStreamAndGenerateTreeSimpleNode(): Tree {
        return readStreamAndGenerateFromFunction { totalVertexes: Int -> Tree(totalVertexes) }
    }
}