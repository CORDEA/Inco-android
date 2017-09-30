package jp.cordea.inco

import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HashGeneratorTest {

    @Test
    fun generate() {
        val generator = HashGenerator()

        assertEquals(generator.generate("hoge"), generator.generate("hoge"))

        assertEquals(generator.generate("huge"), generator.generate("huge"))

        assertNotEquals(generator.generate("huge"), generator.generate("hoge"))
    }
}
