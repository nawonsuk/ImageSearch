package com.mystory.imagesearch.model

import com.mystory.imagesearch.data.searchData
import io.reactivex.Single
import rx.Observable

/**
 * ApiManager
 * @author wsseo
 * @since 2019. 3. 14
 **/
class ApiManager(private val apiService: ApiService) : ApiRepository {
    override fun requestImageSearch(query: String, page: Int, size: Int): Observable<searchData>
            = apiService.requestImageSearch(query, page, size)
}