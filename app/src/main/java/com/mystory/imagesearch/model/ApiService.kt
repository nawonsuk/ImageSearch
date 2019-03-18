package com.mystory.imagesearch.model

import com.mystory.imagesearch.data.searchData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import rx.Observable

/**
* API Service interface
* @author wsseo
* @since 2019. 3. 14
**/
interface ApiService {
    @Headers("Authorization: KakaoAK 76b914b66b4985c2eb02993ff716ef72")
    @GET("/v2/search/image")
    fun requestImageSearch(@Query("query")query:String, @Query("page")page:Int, @Query("size")size:Int): Observable<searchData>
}