package com.mystory.imagesearch.domain

import androidx.lifecycle.MutableLiveData
import com.mystory.imagesearch.presentation.adapter.SearchDataSource

/**
 * ImageSearchListUseCases
 * @author wsseo
 * @since 2019. 3. 14
 **/
interface ImageSearchListUseCases {
    fun getSearchDataSource(query:String, state: MutableLiveData<Int>) : SearchDataSource
}