package jp.cordea.inco

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import jp.cordea.inco.dao.HistoryDao
import jp.cordea.inco.dao.RuleDao
import jp.cordea.inco.models.History
import jp.cordea.inco.models.Rule

@Database(entities = [(History::class), (Rule::class)], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    abstract fun ruleDao(): RuleDao
}
