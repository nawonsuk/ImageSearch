package com.mystory.imagesearch.repository

import com.mystory.imagesearch.Config
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
* retrofit manager
* @author wsseo
* @since 2019. 3. 17
**/
object RetrofitManager {
    val TIMEOUT = 15.toLong()
    private var retrofit: Retrofit.Builder
    private var okHttpClient:OkHttpClient
    private var apiService:ApiService
    init {
        var httpIntercepter = HttpLoggingInterceptor()
        httpIntercepter.level = HttpLoggingInterceptor.Level.BODY

        okHttpClient = OkHttpClient().newBuilder().apply {
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(httpIntercepter)
        }.build()

        retrofit = Retrofit.Builder().apply {
            baseUrl(Config.BASE_URL)
            addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            client(okHttpClient)
        }

        apiService = retrofit.build().create(ApiService::class.java)
    }

    fun getApiService():ApiService {
        return apiService
    }
}