package com.mystory.imagesearch.model

import com.mystory.imagesearch.data.searchData
import rx.Observable

/**
 * API Service interface
 * @author wsseo
 * @since 2019. 3. 14
 **/
interface ApiRepository {
    fun requestImageSearch(query:String, page:Int, size:Int) : Observable<searchData>
}