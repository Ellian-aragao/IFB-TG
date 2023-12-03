package aragao.ellian.github.graph.entrypoint

import aragao.ellian.github.graph.entity.MatrixAdjacent
import java.io.File

class CliInterpreter(args: Array<String>) {
    private val fileInputPath: String
    private var file: File? = null

    init {
        if (args.size != 1)
            throw RuntimeException("Should has 1 argument, the input file graph")
        this.fileInputPath = args[0]
    }

    private fun getInstanceOfFile(): File {
        if (file == null)
            file = File(fileInputPath)

        return file!!
    }

    fun readAllFileMatrixData(): List<String> = getInstanceOfFile().readLines()

    fun readStreamAndGenerateMatrix(): MatrixAdjacent {
        getInstanceOfFile().bufferedReader().use {
            val firstLine = it.readLine()
            val numberOfVertexes = Integer.parseInt(firstLine)
            val matrixAdjacent = MatrixAdjacent(numberOfVertexes)
            var readedLine = it.readLine()
            do {
                val vertexList = readedLine.split(" ")
                val vertex1 = Integer.parseInt(vertexList[0]) - 1
                val vertex2 = Integer.parseInt(vertexList[1]) - 1
                matrixAdjacent.addEdge(vertex1, vertex2)
                readedLine = it.readLine()
            } while (readedLine != null)
            return matrixAdjacent
        }
    }
}