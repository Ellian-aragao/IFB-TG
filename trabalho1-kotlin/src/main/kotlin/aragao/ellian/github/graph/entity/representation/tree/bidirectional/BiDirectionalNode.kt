package aragao.ellian.github.graph.entity.representation.tree.bidirectional

class BiDirectionalNode(val vertex: Int) {
    private val backBiDirectionalNodes = HashSet<BiDirectionalNode>()
    private val nextBiDirectionalNodes = HashSet<BiDirectionalNode>()

    fun addNextNode(nextBiDirectionalNode: BiDirectionalNode) = nextBiDirectionalNodes.add(nextBiDirectionalNode)
    fun addBackNode(backBiDirectionalNode: BiDirectionalNode) = backBiDirectionalNodes.add(backBiDirectionalNode)

    fun getVertexDegree() = backBiDirectionalNodes.size + nextBiDirectionalNodes.size
}

