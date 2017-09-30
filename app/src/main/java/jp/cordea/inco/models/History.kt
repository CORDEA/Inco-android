package jp.cordea.inco.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
class History(
        @PrimaryKey val url: String,
        count: Int,
        val createdAt: Date,
        val updatedAt: Date
) {
    @Ignore
    constructor(url: String) : this(url, 1, Date(), Date())

    var count: Int = count
        private set

    fun inc() = apply {
        count += 1
    }
}
