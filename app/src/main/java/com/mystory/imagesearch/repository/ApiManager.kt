package com.mystory.imagesearch.repository

import com.mystory.imagesearch.Config
import com.mystory.imagesearch.data.searchData
import rx.Observable

/**
 * API Service manager
 * @author wsseo
 * @since 2019. 3. 14
 **/
object ApiManager {
    var apiService = RetrofitManager.getApiService()

    /**
     * 이미지 검색 데이타를 요청한다.
     */
    fun requestImageSearch(query:String, page:Int, size:Int= Config.PAGE_SIZE): Observable<searchData> {
        return apiService.requestImageSearch(query, page, size)
    }

}