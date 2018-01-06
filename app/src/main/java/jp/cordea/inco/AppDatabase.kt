package jp.cordea.inco

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import jp.cordea.inco.dao.RuleDao
import jp.cordea.inco.models.Rule

@Database(entities = [(Rule::class)], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ruleDao(): RuleDao
}
