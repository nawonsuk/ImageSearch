package com.mystory.imagesearch.di.net

import com.mystory.imagesearch.model.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * NetModule
 * @author wsseo
 * @since 2019. 3. 14
 **/
private const val BASE_URL = "https://dapi.kakao.com"

@Module
class NetModule {

    @Provides
    fun providesApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient) =
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()


    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
    }
}