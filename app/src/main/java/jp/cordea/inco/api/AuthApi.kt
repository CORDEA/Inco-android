package jp.cordea.inco.api

import jp.cordea.inco.models.History
import retrofit2.Call
import retrofit2.http.GET

interface AuthApi {

    @GET("histories")
    fun getHistories(): Call<List<History>>
}
