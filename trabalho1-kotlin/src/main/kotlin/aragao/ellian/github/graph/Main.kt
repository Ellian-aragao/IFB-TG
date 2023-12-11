package aragao.ellian.github.graph

import aragao.ellian.github.graph.infrastructure.entrypoint.CliInterpreter

fun main() {
    val args = arrayOf("data/input_trabalho1.txt")
    val cliInterpreter = CliInterpreter(args)
    val graphData = cliInterpreter.readStreamAndGenerateGraphData()
    cliInterpreter.writeFileReportFromGraph(graphData)
}
