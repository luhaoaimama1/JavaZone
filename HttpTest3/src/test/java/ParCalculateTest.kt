import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals

@RunWith(Parameterized::class)
class ParCalculateTest {

    var expect = 0
    var input1 = 0
    var input2 = 0

    constructor(expect: Int, input1: Int, input2: Int) {
        this.expect = expect
        this.input1 = input1
        this.input2 = input2
    }

    companion object {
        @Parameterized.Parameters
        @JvmStatic
        fun coll() = arrayListOf(
                arrayOf(3, 1, 2),
                arrayOf(4, 2, 2)
        )
    }


    @Test
    fun testAdd() {
        assertEquals(expect, Calculate().add(input1, input2))
    }
}