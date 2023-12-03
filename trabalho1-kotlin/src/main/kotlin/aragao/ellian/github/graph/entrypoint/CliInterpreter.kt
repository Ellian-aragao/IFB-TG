package aragao.ellian.github.graph.entrypoint

import aragao.ellian.github.graph.entity.AdjacentList
import aragao.ellian.github.graph.entity.GraphAbstract
import aragao.ellian.github.graph.entity.AdjacentMatrix
import java.io.File
import java.util.function.Function

class CliInterpreter(args: Array<String>) {
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

    private fun readStreamAndGenerateMatrix(function: Function<Int, GraphAbstract>): GraphAbstract {
        getInstanceOfFileInput().bufferedReader().use {
            val firstLine = it.readLine()
            val numberOfVertexes = Integer.parseInt(firstLine)
            val graphAbstract = function.apply(numberOfVertexes)
            var readedLine = it.readLine()
            do {
                val vertexList = readedLine.split(" ")
                val vertex1 = Integer.parseInt(vertexList[0]) - 1
                val vertex2 = Integer.parseInt(vertexList[1]) - 1
                graphAbstract.addEdge(vertex1, vertex2)
                readedLine = it.readLine()
            } while (readedLine != null)
            return graphAbstract
        }
    }

    fun readStreamAndGenerateAdjacentMatrix(): GraphAbstract {
        return readStreamAndGenerateMatrix { totalVertexes: Int -> AdjacentMatrix(totalVertexes) }
    }

    fun readStreamAndGenerateAdjacentList(): GraphAbstract {
        return readStreamAndGenerateMatrix { totalVertexes: Int -> AdjacentList(totalVertexes) }
    }

    fun writeFileReportFromGraph(graph: GraphAbstract) {
        val totalVertex = graph.getTotalVertex()
        val edgeLenght = graph.getEdgeLenght()
        val vertexDegress = graph.processVertexDegreesAndGet()
        getInstanceOfFileOutput().bufferedWriter().use {
            it.write("# n = $totalVertex")
            it.newLine()
            it.write("# m = $edgeLenght")
            it.newLine()
            vertexDegress.forEachIndexed { vertex, degree ->
                it.write("${vertex + 1} $degree")
                it.newLine()
            }
        }
    }
}