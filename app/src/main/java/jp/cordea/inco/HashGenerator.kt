package jp.cordea.inco

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class HashGenerator {

    companion object {
        private val Secret = "IncoSecret"
        private val Algorithm = "HmacSHA256"
    }

    fun generate(text: String): String {
        val key = SecretKeySpec(Secret.toByteArray(Charsets.UTF_8), Algorithm)
        val mac = Mac.getInstance(Algorithm)
        mac.init(key)
        return mac.doFinal(text.toByteArray(Charsets.UTF_8)).toString(Charsets.UTF_8)
    }
}