package aragao.ellian.github.graph

import aragao.ellian.github.graph.entrypoint.CliInterpreter
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val args = arrayOf("/home/ellian/code/faculdade/IFB-TG/trabalho1/data/input_trabalho1.txt")
    val cliInterpreter = CliInterpreter(args)
    val matrix = cliInterpreter.readStreamAndGenerateAdjacentList()
    cliInterpreter.writeFileReportFromGraph(matrix)
}