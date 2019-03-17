package com.mystory.imagesearch.view.adapter

import android.util.Log
import androidx.paging.PositionalDataSource
import com.mystory.imagesearch.data.document
import com.mystory.imagesearch.data.searchData
import com.mystory.imagesearch.repository.ApiManager
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
/**
 * search data source
 * @author wsseo
 * @since 2019. 3. 17
 **/
class SearchDataSource : PositionalDataSource<document>() {
    var searchData:searchData? = null

    fun setData(data: searchData){
        this.searchData = data
    }
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<document>) {
        searchData?.let {
            it.documents?.let { document ->
                if(document.size > params.pageSize)
                    callback.onResult(document.subList(0, params.pageSize), 0, document.size)
                else
                    callback.onResult(document.subList(0, document.size), 0, document.size)
            }
        }
    }
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<document>) {
        searchData?.let {
            it.documents?.let { document ->
                callback.onResult(document.subList(params.startPosition, params.startPosition + params.loadSize))
            }
        }
    }

}
