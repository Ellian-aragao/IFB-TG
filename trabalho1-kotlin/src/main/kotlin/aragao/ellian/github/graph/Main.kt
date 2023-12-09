package aragao.ellian.github.graph

import aragao.ellian.github.graph.entrypoint.CliInterpreter

fun main() {
    val args = arrayOf("data/input_trabalho1.txt")
    val cliInterpreter = CliInterpreter(args)
    val tree = cliInterpreter.readStreamAndGenerateTreeSimpleNode()
    val searchList = tree.breadthFirstSearch()
    println(searchList)
    val adjacentMatrix = cliInterpreter.readStreamAndGenerateAdjacentMatrix()
    val searchList2 = adjacentMatrix.breadthFirstSearch()
    println(searchList2)
    println(adjacentMatrix)
}

fun writeReport(cliInterpreter: CliInterpreter) {
    val matrix = cliInterpreter.readStreamAndGenerateAdjacentList()
    cliInterpreter.writeFileReportFromGraph(matrix)
}