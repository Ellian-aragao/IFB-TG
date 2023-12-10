package aragao.ellian.github.graph.domain.usecase

import aragao.ellian.github.graph.domain.entity.representation.AbstractGraph
import kotlin.system.measureNanoTime

class ExecutionTimeSearchGraphUseCase {

    fun executionTimeDepthFirstSearchGraph(graph: AbstractGraph, startVertex: Int = 0): Long {
        return measureNanoTime {
            graph.depthFirstSearch(startVertex)
        }
    }

    fun executionTimeBreadFirstSearchGraph(graph: AbstractGraph, startVertex: Int = 0): Long {
        return measureNanoTime {
            graph.breadthFirstSearch(startVertex)
        }
    }
}