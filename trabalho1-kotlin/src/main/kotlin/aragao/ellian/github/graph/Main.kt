package aragao.ellian.github.graph

import aragao.ellian.github.graph.entity.tree.Tree
import aragao.ellian.github.graph.entrypoint.CliInterpreter

fun main(): Unit {
    val args = arrayOf("data/input_trabalho1.txt")
    val cliInterpreter = CliInterpreter(args)
    val tree = cliInterpreter.readStreamAndGenerateTreeSimpleNode()
}

fun writeReport(cliInterpreter: CliInterpreter) {
    val matrix = cliInterpreter.readStreamAndGenerateAdjacentList()
    cliInterpreter.writeFileReportFromGraph(matrix)
}