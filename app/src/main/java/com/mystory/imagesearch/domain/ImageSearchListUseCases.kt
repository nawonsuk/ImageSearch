package com.mystory.imagesearch.domain

import com.mystory.imagesearch.presentation.adapter.SearchDataSource

/**
 * ImageSearchListUseCases
 * @author wsseo
 * @since 2019. 3. 14
 **/
interface ImageSearchListUseCases {
    fun getSearchListBy(query:String) : SearchDataSource
}