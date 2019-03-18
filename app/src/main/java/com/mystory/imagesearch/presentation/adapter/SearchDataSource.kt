package com.mystory.imagesearch.presentation.adapter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.mystory.imagesearch.Config
import com.mystory.imagesearch.data.document
import com.mystory.imagesearch.data.searchData
import com.mystory.imagesearch.model.ApiRepository
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * search data source
 * @author wsseo
 * @since 2019. 3. 17
 **/
class SearchDataSource(apiRepository: ApiRepository, state:MutableLiveData<Int>): PositionalDataSource<document>() {
    var apiRepository = apiRepository
    var searchData = MutableLiveData<searchData>()
    var state:MutableLiveData<Int> = state
    var query:String?=null
    var totalSize:Int = 0
    fun searchQuery(query:String){
        this.query = query
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<document>) {
        query?.let { requestImageSearch(it, 1, params.pageSize, callback, null) }
    }
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<document>) {
        query?.let {
            var position = params.startPosition/params.loadSize+1
            if(totalSize < params.loadSize) position = params.startPosition + 1
            requestImageSearch(it, position, params.loadSize, null, callback)
        }
    }

    fun requestImageSearch(query:String, page:Int, size:Int,
                           callbackInit: LoadInitialCallback<document>?,
                           callback: LoadRangeCallback<document>?){
        apiRepository.requestImageSearch(query,page,size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                searchData.value = data
                data.meta?.let {
                    if(it.total_count == 0) state.value = Config.STATE_SEARCH_NO_RESULT
                    totalSize = it.total_count
                }
                data.documents?.let { document ->
                    callbackInit?.let {
                        if(totalSize < Config.PAGE_SIZE)
                            it.onResult(document, 0)
                        else
                            it.onResult(document, 0, totalSize)
                    }
                    callback?.let {
                        if(totalSize >= Config.PAGE_SIZE) it.onResult(document)
                    }
                    state.value = Config.STATE_SEARCH_COMPLETE_NORMAL
                }
            }, {
                state.value = Config.STATE_SEARCH_ERROR
            })
    }
}
