package aragao.ellian.github.graph

import aragao.ellian.github.graph.domain.usecase.ExecutionTimeSearchGraphUseCase
import aragao.ellian.github.graph.infrastructure.entrypoint.CliInterpreter
import kotlin.math.abs

fun main() {
    val args = arrayOf("data/input_trabalho1.txt")
    val cliInterpreter = CliInterpreter(args)
    val adjacentMatrix = cliInterpreter.readStreamAndGenerateAdjacentMatrix()
    val adjacentList = cliInterpreter.readStreamAndGenerateAdjacentList()
    val timeSearchUseCase = ExecutionTimeSearchGraphUseCase()
    val matrixSearchTime = timeSearchUseCase.executionTimeBreadFirstSearchGraph(adjacentMatrix)
    val listSearchTime = timeSearchUseCase.executionTimeBreadFirstSearchGraph(adjacentList)
    println(matrixSearchTime)
    println(listSearchTime)
    println(abs(matrixSearchTime - listSearchTime))
}

fun writeReport(cliInterpreter: CliInterpreter) {
    val matrix = cliInterpreter.readStreamAndGenerateAdjacentList()
    cliInterpreter.writeFileReportFromGraph(matrix)
}