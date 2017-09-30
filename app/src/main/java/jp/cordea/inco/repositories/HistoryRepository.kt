package jp.cordea.inco.repositories

import android.content.Context
import jp.cordea.inco.CipherUtils
import jp.cordea.inco.IncoApplication
import jp.cordea.inco.Key
import jp.cordea.inco.models.History
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

object HistoryRepository {

    private val historyDao = IncoApplication.Database.historyDao()

    fun getHistories(): Deferred<List<History>> = async(CommonPool) {
        historyDao.getHistories()
    }

    fun updateHistory(url: String, cipherKey: String, nonce: String): Deferred<Unit> =
            async(CommonPool) {
                val encryptedUrl = CipherUtils.encrypt(url, cipherKey, nonce)
                val oldHistory = historyDao.getHistory(encryptedUrl)
                oldHistory?.let {
                    historyDao.updateHistory(incrementAccessCount(it))
                    return@async
                }
                historyDao.insertHistory(History(encryptedUrl))
            }

    fun deleteHistory(history: History): Deferred<Unit> =
            async(CommonPool) {
                historyDao.deleteHistory(history)
            }

    fun deleteHistory(url: String): Deferred<Unit> =
            async(CommonPool) {
                historyDao.deleteHistoryByUrl(url)
            }

    fun decryptUrl(url: String, cipherKey: String, nonce: String): String =
            CipherUtils.decrypt(url, cipherKey, nonce)

    fun refreshNonce(context: Context): String =
            CipherUtils.generateNonce().also {
                Key.setNonce(context, it)
            }

    private fun incrementAccessCount(history: History): History = history.inc()
}
