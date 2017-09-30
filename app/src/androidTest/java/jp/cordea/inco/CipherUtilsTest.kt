package jp.cordea.inco

import android.support.test.runner.AndroidJUnit4
import android.util.Base64
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CipherUtilsTest {

    @Test
    fun generateKey() {
        val key1 = CipherUtils.generateKey()
        val key2 = CipherUtils.generateKey()
        assertEquals(32, Base64.decode(key1, Base64.DEFAULT).size)

        assertEquals(32, Base64.decode(key2, Base64.DEFAULT).size)

        assertNotEquals(key1, key2)
    }

    @Test
    fun generateNonce() {
        val nonce1 = CipherUtils.generateNonce()
        val nonce2 = CipherUtils.generateNonce()

        assertEquals(12, Base64.decode(nonce1, Base64.DEFAULT).size)

        assertEquals(12, Base64.decode(nonce2, Base64.DEFAULT).size)

        assertNotEquals(nonce1, nonce2)
    }

    @Test
    fun cipherScenario() {
        val key = CipherUtils.generateKey()
        val text = "hoge"
        val nonce = CipherUtils.generateNonce()
        val encrypted = CipherUtils.encrypt(text, key, nonce)
        val decrypted = CipherUtils.decrypt(encrypted, key, nonce)

        assertEquals(text, decrypted)
    }
}
