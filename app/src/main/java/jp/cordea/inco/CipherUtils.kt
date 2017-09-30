package jp.cordea.inco

import android.util.Base64
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

class CipherUtils {

    companion object {
        private val cipher = Cipher.getInstance("AES/GCM/NoPadding")

        private fun getSecretKeySpec(key: String): SecretKeySpec =
                Base64.decode(key, Base64.DEFAULT).let {
                    SecretKeySpec(it, "AES")
                }

        private fun getGcmParameterSpec(nonce: String): GCMParameterSpec =
                Base64.decode(nonce, Base64.DEFAULT).let {
                    GCMParameterSpec(128, it)
                }

        fun generateKey(): String {
            val random = SecureRandom()
            val bytes = ByteArray(256 / 8)
            random.nextBytes(bytes)
            return Base64.encode(bytes, Base64.DEFAULT).toString(Charsets.ISO_8859_1)
        }

        fun generateNonce(): String {
            val random = SecureRandom()
            val bytes = ByteArray(96 / 8)
            random.nextBytes(bytes)
            return Base64.encode(bytes, Base64.DEFAULT).toString(Charsets.ISO_8859_1)
        }

        fun encrypt(text: String, key: String, nonce: String): String {
            val spec = getSecretKeySpec(key)
            val gcmSpec = getGcmParameterSpec(nonce)
            cipher.init(Cipher.ENCRYPT_MODE, spec, gcmSpec)
            val bytes = cipher.doFinal(text.toByteArray(Charsets.ISO_8859_1))
            return Base64.encodeToString(bytes, Base64.DEFAULT)
        }

        fun decrypt(text: String, key: String, nonce: String): String {
            val decoded = Base64.decode(text, Base64.DEFAULT)
            val spec = getSecretKeySpec(key)
            val gcmSpec = getGcmParameterSpec(nonce)
            cipher.init(Cipher.DECRYPT_MODE, spec, gcmSpec)
            return cipher.doFinal(decoded).toString(Charsets.ISO_8859_1)
        }
    }
}
