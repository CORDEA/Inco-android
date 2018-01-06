package jp.cordea.inco.models

import com.squareup.moshi.Json

data class History(
        val id: Long,
        val url: String,
        @field:Json(name = "created_at") val createdAt: String,
        @Transient var count: Int
)
