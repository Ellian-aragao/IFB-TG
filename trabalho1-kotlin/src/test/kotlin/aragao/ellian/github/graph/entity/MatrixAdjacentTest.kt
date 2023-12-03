package aragao.ellian.github.graph.entity

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class MatrixAdjacentTest {

    @Test
    fun `should throw error when pass value negative`() {
        val exception = assertThrows(RuntimeException::class.java) { MatrixAdjacent(-1) }
        assertEquals("-1", exception.message)
    }

    @Test
    fun `should throw error when create matrix and add vertex with wrong bounds`() {
        val matrixAdjacent = MatrixAdjacent(1)
        val exception = assertThrows(RuntimeException::class.java) { matrixAdjacent.addEdge(0, 3) }
        assertEquals("Bounds of vertex shound be major than 0 and minour than 1, the entry value was (0, 3)", exception.message)
        val exception2 = assertThrows(RuntimeException::class.java) { matrixAdjacent.addEdge(1, 0) }
        assertEquals("Bounds of vertex shound be major than 0 and minour than 1, the entry value was (1, 0)", exception2.message)
    }

    @Test
    fun `should validate process vertex degree values`() {
        val matrixAdjacent = MatrixAdjacent(3)
        matrixAdjacent
            .addEdge(0, 1)
            .addEdge(0, 2)
            .addEdge(1, 2)
        assertContentEquals(arrayOf(0, 0, 0), matrixAdjacent.getVertexDegress())
        matrixAdjacent.processAllVertexesDegrees()
        assertContentEquals(arrayOf(2, 2, 2), matrixAdjacent.getVertexDegress())
    }

    @Test
    fun `should validate pre process vertex degree values`() {
        val matrixAdjacent = MatrixAdjacent(3, true)
        matrixAdjacent
            .addEdge(0, 1)
            .addEdge(0, 2)
            .addEdge(1, 2)
        assertContentEquals(arrayOf(2, 2, 2), matrixAdjacent.getVertexDegress())
    }
}