package aragao.ellian.github.graph

import aragao.ellian.github.graph.entrypoint.CliInterpreter

fun main(args: Array<String>) {
    val matrix =
        CliInterpreter(arrayOf("/home/ellian/code/faculdade/IFB-TG/trabalho1/data/input_trabalho1.txt"))
            .readStreamAndGenerateMatrix()
    matrix.processAllVertexesDegrees()
}