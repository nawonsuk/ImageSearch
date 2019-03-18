package com.mystory.imagesearch.domain

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.mystory.imagesearch.model.ApiRepository
import com.mystory.imagesearch.presentation.adapter.SearchDataSource

/**
 * ImageSearchListInteractor
 * @author wsseo
 * @since 2019. 3. 14
 **/
class ImageSearchListInteractor(private val apiRepository: ApiRepository) : ImageSearchListUseCases {
    override fun getSearchDataSource(query:String, state:MutableLiveData<Int>): SearchDataSource {
        var searchDataSource = SearchDataSource(apiRepository, state)
        searchDataSource.searchQuery(query)
        return searchDataSource
    }
}