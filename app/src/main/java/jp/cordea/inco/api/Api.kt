package jp.cordea.inco.api

import jp.cordea.inco.models.Token
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("login")
    fun login(@Query("user") username: String, @Query("pass") password: String): Call<Token>

    @FormUrlEncoded
    @POST("histories")
    fun postHistory(@Field("url") url: String): Call<Any>
}
