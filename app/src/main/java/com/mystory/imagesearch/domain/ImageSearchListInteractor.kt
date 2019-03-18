package com.mystory.imagesearch.domain

import com.mystory.imagesearch.model.ApiRepository
import com.mystory.imagesearch.presentation.adapter.SearchDataSource

/**
 * ImageSearchListInteractor
 * @author wsseo
 * @since 2019. 3. 14
 **/
class ImageSearchListInteractor(private val apiRepository: ApiRepository) : ImageSearchListUseCases {
    override fun getSearchListBy(query:String): SearchDataSource {
        var searchDataSource = SearchDataSource(apiRepository)
        searchDataSource.searchQuery(query)
        return searchDataSource
    }
}