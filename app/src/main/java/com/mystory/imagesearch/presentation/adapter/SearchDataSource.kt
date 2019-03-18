package com.mystory.imagesearch.presentation.adapter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
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
class SearchDataSource(apiRepository: ApiRepository): PositionalDataSource<document>() {
    var apiRepository = apiRepository
    var toastMsg = MutableLiveData<String>()
    var searchData = MutableLiveData<searchData>()
    var query:String?=null
    fun searchQuery(query:String){
        this.query = query
    }
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<document>) {
        Log.d("loadRange","loadInitial : ${params.pageSize}")
        query?.let { requestImageSearch(it, 1, params.pageSize, callback, null) }
    }
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<document>) {
        Log.d("loadRange","loadRange : ${params.startPosition/params.loadSize+1} , paramsize : ${params.loadSize}")
        query?.let { requestImageSearch(it, params.startPosition/params.loadSize+1, params.loadSize, null, callback) }
    }

    fun requestImageSearch(query:String, page:Int, size:Int,
                           callbackInit: LoadInitialCallback<document>?,
                           callback: LoadRangeCallback<document>?){
        apiRepository.requestImageSearch(query,page,size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                var totalSize:Int = 0
                searchData.value = data
                data.meta?.let {
                    if(it.total_count == 0) toastMsg.value = "이미지 검색 결과가 없습니다."
                    totalSize = it.total_count
                }
                data.documents?.let { document ->
                    callbackInit?.onResult(document, 0, totalSize)
                    callback?.onResult(document)
                }
            }, {
                toastMsg.value = "오류가 발생되었습니다."
            })
    }
}
