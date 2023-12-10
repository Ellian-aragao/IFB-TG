package aragao.ellian.github.graph.entity

data class GraphEdge(val vertex1: Int, val vertex2: Int) {
    init {
        if ((vertex1 < 0) or (vertex2 < 0)) throw RuntimeException("Vertex could not be less than 0")
    }
}
