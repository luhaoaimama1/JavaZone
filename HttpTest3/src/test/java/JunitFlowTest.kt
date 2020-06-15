import org.junit.*

class JunitFlowTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun funBeforeClass() {
            println("funBeforeClass...")
        }

        @AfterClass
        @JvmStatic
        fun funAfterClass() {
            println("funAfterClass...")
        }
    }

    @Before
    fun funBefore() {
        println("funBefore...")
    }

    @After
    fun funAfter() {
        println("funAfter...")
    }

    @Test
    fun funTest() {
        println("funTest...")
    }

    @Test
    fun funTest2() {
        println("funTest2...")
    }

}