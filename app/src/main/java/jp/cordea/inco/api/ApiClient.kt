package jp.cordea.inco.api

import com.squareup.moshi.Moshi
import jp.cordea.inco.BuildConfig
import jp.cordea.inco.Key
import jp.cordea.inco.models.History
import jp.cordea.inco.models.Token
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient : Api, AuthApi {

    private val moshi = Moshi
            .Builder()
            .build()

    private val client = OkHttpClient
            .Builder()
            .addInterceptor {
                it.proceed(it.request()
                        .newBuilder()
                        .addHeader("Authorization", "Bearer " + Key.token)
                        .build()
                )
            }
            .build()

    private val retrofitBuilder = Retrofit
            .Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))

    private val service = retrofitBuilder
            .build()
            .create(Api::class.java)

    private val authService = retrofitBuilder
            .client(client)
            .build()
            .create(AuthApi::class.java)

    override fun login(username: String, password: String): Call<Token> =
            service.login(username, password)

    override fun getHistories(): Call<List<History>> =
            authService.getHistories()

    override fun postHistory(url: String): Call<Any> =
            service.postHistory(url)
}
