package jp.cordea.inco

import android.content.Context
import android.preference.PreferenceManager

object Key {

    private const val JavaScriptKey = "JavaScriptKey"

    var token = ""
        get() {
            if (field.isBlank()) {
                throw IllegalStateException()
            }
            return field
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
