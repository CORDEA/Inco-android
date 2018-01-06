package jp.cordea.inco

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CipherUtilsTest {

    private val context = InstrumentationRegistry.getContext()

    @Test
    fun cipherScenario() {
        val text = "hoge"
        val encrypted = "n1QJEOqN43ITg7Tkd7JVXyWDXJdbhfVNrLWZTkOy+tOzTxL+n+c2yqaVbs9batLpz17E" +
                "838cFCz11wsZB+T8eeJTDl5jJIKpn62M0nAApkYCkWDYRYglnYVNGoq+Pp6of1bfkp0+e7EcNxqW" +
                "6M+xODq1k7y5MZZPMoiCExDlaUA="

        val key = CipherUtils.readPem(context, jp.cordea.inco.test.R.raw.private_pem)
        val decrypted = CipherUtils.decrypt(encrypted, key)

        assertEquals(text, decrypted)
    }
}
