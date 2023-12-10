package aragao.ellian.github.graph.domain.entity

data class GraphData(val totalVertexes: Int, val edges: Collection<GraphEdge>) {
    companion object {
        @JvmStatic
        fun builder(): GraphDataBuilder {
            return GraphDataBuilder()
        }
    }

    class GraphDataBuilder {
        private var totalVertexes: Int = 0
        private var edges: MutableCollection<GraphEdge> = mutableListOf()

        fun totalVertexes(totalVertexes: Int): GraphDataBuilder {
            if (totalVertexes < 0) throw RuntimeException("Could not create GraphData with total vertex less than 0")
            this.totalVertexes = totalVertexes
            return this
        }

        fun addEdge(vertex1: Int, vertex2: Int): GraphDataBuilder {
            edges.add(GraphEdge(vertex1, vertex2))
            return this
        }

        fun edges(edges: Collection<GraphEdge>): GraphDataBuilder {
            this.edges = edges.toMutableList()
            return this
        }

        fun build(): GraphData {
            return GraphData(totalVertexes, edges.toList())
        }
    }
}
