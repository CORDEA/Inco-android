package jp.cordea.inco

import android.content.Context
import android.support.annotation.RawRes
import android.util.Base64
import java.io.InputStreamReader
import java.security.KeyFactory
import java.security.spec.PKCS8EncodedKeySpec
import javax.crypto.Cipher

object CipherUtils {

    private val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")

    fun readPem(context: Context, @RawRes resId: Int): String =
            InputStreamReader(
                    context.resources.openRawResource(resId)).use {
                it.readText()
            }.replace("-----BEGIN RSA PRIVATE KEY-----", "")
                    .replace("-----END RSA PRIVATE KEY-----", "")
                    .replace("\n", "")

    fun decrypt(text: String, key: String): String {
        val decodedKey = Base64.decode(key, Base64.DEFAULT)
        val decodedText = Base64.decode(text, Base64.DEFAULT)
        val factory = KeyFactory.getInstance("RSA")

        val spec = PKCS8EncodedKeySpec(decodedKey)
        val privateKey = factory.generatePrivate(spec)
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        return cipher.doFinal(decodedText).toString(Charsets.UTF_8).trimEnd('\n')
    }
}
