package jp.cordea.inco.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
class Rule(
        @PrimaryKey val regex: String,
        val createdAt: Date,
        val updatedAt: Date
) {
    @Ignore
    constructor(regex: String) : this(regex, Date(), Date())
}
