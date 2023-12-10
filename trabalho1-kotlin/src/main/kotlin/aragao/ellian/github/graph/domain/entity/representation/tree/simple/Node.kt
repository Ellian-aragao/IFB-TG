package aragao.ellian.github.graph.domain.entity.representation.tree.simple

class Node(val vertex: Int) {
    private val edgesWithNodes = HashSet<Node>()

    fun addEdge(node: Node) = edgesWithNodes.add(node)

    fun getVertexDegree() = edgesWithNodes.size

    fun forEachEdgesNodes(action: (Node) -> Unit) {
        edgesWithNodes.forEach(action)
    }
}

