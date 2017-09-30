package jp.cordea.inco.dao

import android.arch.persistence.room.*
import jp.cordea.inco.models.History

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history")
    fun getHistories(): List<History>

    @Query("SELECT * FROM history WHERE url = :url LIMIT 1")
    fun getHistory(url: String): History?

    @Query("DELETE FROM history WHERE url = :url")
    fun deleteHistoryByUrl(url: String)

    @Insert
    fun insertHistory(history: History)

    @Update
    fun updateHistory(history: History)

    @Delete
    fun deleteHistory(history: History)
}
