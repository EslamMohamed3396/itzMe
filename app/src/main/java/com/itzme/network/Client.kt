package com.dayrah.network

import com.dayrah.utilis.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object Client {

    private var sBuilder: OkHttpClient.Builder? = null

    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder? {
        return try {
            if (sBuilder == null) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                sBuilder = OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
            }
            sBuilder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun getClient(): Api {
        return Retrofit.Builder()
            .client(getUnsafeOkHttpClient()?.build()!!)
            .baseUrl(Constant.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

}