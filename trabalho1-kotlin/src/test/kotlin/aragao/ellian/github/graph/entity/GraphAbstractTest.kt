package aragao.ellian.github.graph.entity

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

abstract class GraphAbstractTest {

    abstract fun provide(totalVertexes: Int): GraphAbstract

    @Test
    fun `should throw error when pass value negative`() {
        val exception = assertThrows(RuntimeException::class.java) { provide(-1) }
        assertEquals("-1", exception.message)
    }

    @Test
    fun `should throw error when create matrix and add vertex with wrong bounds`() {
        val graphAbstract = provide(1)
        val exception = assertThrows(RuntimeException::class.java) { graphAbstract.addEdge(0, 3) }
        assertEquals(
            "Bounds of vertex shound be major than 0 and minour than 1, the entry value was (0, 3)",
            exception.message
        )
        val exception2 = assertThrows(RuntimeException::class.java) { graphAbstract.addEdge(1, 0) }
        assertEquals(
            "Bounds of vertex shound be major than 0 and minour than 1, the entry value was (1, 0)",
            exception2.message
        )
    }

    @Test
    fun `should validate process vertex degree values`() {
        val graphAbstract = provide(3)
        graphAbstract
            .addEdge(0, 1)
            .addEdge(0, 2)
            .addEdge(1, 2)
        assertContentEquals(arrayOf(0, 0, 0), graphAbstract.getVertexDegress())
        graphAbstract.processAllVertexesDegrees()
        assertContentEquals(arrayOf(2, 2, 2), graphAbstract.getVertexDegress())
    }
}