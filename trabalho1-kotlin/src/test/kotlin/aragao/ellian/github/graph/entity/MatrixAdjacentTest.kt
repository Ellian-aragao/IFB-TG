package aragao.ellian.github.graph.entity

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class MatrixAdjacentTest : GraphAbstractTest() {
    override fun provide(totalVertexes: Int): GraphAbstract {
        return MatrixAdjacent(totalVertexes)
    }

    @Test
    fun `should validate pre process vertex degree values`() {
        val graphAbstract = MatrixAdjacent(3, true)
        graphAbstract
            .addEdge(0, 1)
            .addEdge(0, 2)
            .addEdge(1, 2)
        assertContentEquals(arrayOf(2, 2, 2), graphAbstract.getVertexDegress())
    }
}