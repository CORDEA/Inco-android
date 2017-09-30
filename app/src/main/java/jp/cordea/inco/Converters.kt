package jp.cordea.inco

import android.arch.persistence.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long): Date = Date(value)

    @TypeConverter
    fun toTimestamp(value: Date): Long = value.time
}
