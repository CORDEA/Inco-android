package jp.cordea.inco

import android.content.Context
import android.preference.PreferenceManager

class Key {

    companion object {
        private val PasswordKey = "PasswordKey"

        private val CipherKeyKey = "CipherKeyKey"

        private val NonceKey = "NonceKey"

        private val JavaScriptKey = "JavaScriptKey"

        fun setPassword(context: Context, password: String) {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            pref.edit().putString(PasswordKey, password).apply()
        }

        fun getPassword(context: Context): String {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            return pref.getString(PasswordKey, "")
        }

        fun setCipherKey(context: Context, cipher: String) {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            pref.edit().putString(CipherKeyKey, cipher).apply()
        }

        fun setNonce(context: Context, nonce: String) {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            pref.edit().putString(NonceKey, nonce).apply()
        }

        fun getNonce(context: Context): String {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            return pref.getString(NonceKey, "")
        }

        fun getCipherKey(context: Context): String {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            return pref.getString(CipherKeyKey, "")
        }

        fun javaScriptEnabled(context: Context): Boolean {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            return pref.getBoolean(JavaScriptKey, false)
        }

        fun setJavaScriptEnabled(context: Context, boolean: Boolean) {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            pref.edit().putBoolean(JavaScriptKey, boolean).apply()
        }
    }
}
