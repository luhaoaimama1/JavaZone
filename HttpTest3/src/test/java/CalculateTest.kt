import org.junit.Test
import kotlin.test.assertEquals

class CalculateTest {
    @Test
    fun testAdd(){
        assertEquals(3,Calculate().add(2,1))
    }

    @Test
    fun testAdd2(){
        assertEquals(3,Calculate().add(1,2))
    }
}