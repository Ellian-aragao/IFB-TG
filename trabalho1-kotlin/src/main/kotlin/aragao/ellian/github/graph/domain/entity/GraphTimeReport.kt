package aragao.ellian.github.graph.domain.entity

import aragao.ellian.github.graph.domain.entity.representation.AbstractGraph
import kotlin.system.measureNanoTime

class GraphTimeReport(private val graph: AbstractGraph) : GraphSimpleReport(graph) {
    val clazz: String = graph.javaClass.simpleName

    fun indexAndExecutionTimeDepthFirstSearchGraphWorstCase(): Pair<Int, Long> {
        val listExecutions = executionTimeDepthFirstSearchGraphAllVertexes()
        val max = listExecutions.max()
        return Pair(listExecutions.indexOf(max), max)
    }

    fun executionTimeDepthFirstSearchGraphAllVertexes(): List<Long> {
        return (0..<graph.getTotalVertex()).map {
            executionTimeDepthFirstSearchGraph(it)
        }
    }

    fun executionTimeDepthFirstSearchGraph(startVertex: Int = 0): Long {
        return measureNanoTime { run { graph.depthFirstSearch(startVertex) } }
    }

    fun indexAndExecutionTimeBreadFirstSearchGraphWorstCase(): Pair<Int, Long> {
        val listExecutions = executionTimeBreadFirstSearchGraphAllVertexes()
        val max = listExecutions.max()
        return Pair(listExecutions.indexOf(max), max)
    }

    fun executionTimeBreadFirstSearchGraphAllVertexes(): List<Long> {
        return (0..<graph.getTotalVertex()).map {
            measureNanoTime { run { graph.breadthFirstSearch(it) } }
        }
    }

    companion object {
        @JvmStatic
        fun of(abstractGraph: AbstractGraph) = GraphTimeReport(abstractGraph)
    }
}
