package aragao.ellian.github.graph

import aragao.ellian.github.graph.entity.tree.Tree
import aragao.ellian.github.graph.entrypoint.CliInterpreter

fun main(): Unit {
    val args = arrayOf("/home/ellian/code/faculdade/IFB-TG/trabalho1/data/input_trabalho1.txt")
    val cliInterpreter = CliInterpreter(args)
    val fileMatrixData = cliInterpreter.readAllFileData()
    val numberOfVertexes = Integer.parseInt(fileMatrixData[0])
    val tree = Tree(numberOfVertexes)
    fileMatrixData.subList(1, fileMatrixData.size).forEach {
        val vertexes = it.split(" ")
        val vertex1 = Integer.parseInt(vertexes[0]) - 1
        val vertex2 = Integer.parseInt(vertexes[1]) - 1
        tree.addEdge(vertex1, vertex2)
    }
    println(tree.depthFirstSearch().joinToString(", "))
    println(tree.breadthFirstSearch().joinToString(", "))
}

fun writeReport(cliInterpreter: CliInterpreter) {
    val matrix = cliInterpreter.readStreamAndGenerateAdjacentList()
    cliInterpreter.writeFileReportFromGraph(matrix)
}