package jp.cordea.inco.repositories

import android.content.Context
import jp.cordea.inco.CipherUtils
import jp.cordea.inco.R
import jp.cordea.inco.api.ApiClient
import jp.cordea.inco.models.History
import retrofit2.Call

object HistoryRepository {

    fun getHistories(): Call<List<History>> =
            ApiClient.getHistories()

    fun postHistory(url: String): Call<Any> =
            ApiClient.postHistory(url)

    fun decryptUrl(context: Context, url: String): String {
        val key = CipherUtils.readPem(context, R.raw.private_pem)
        return CipherUtils.decrypt(url, key)
    }
}
