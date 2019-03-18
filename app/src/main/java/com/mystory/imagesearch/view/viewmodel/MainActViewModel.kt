package com.mystory.imagesearch.view.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.jakewharton.rxbinding2.widget.RxTextView
import com.mystory.imagesearch.data.document
import com.mystory.imagesearch.data.searchData
import com.mystory.imagesearch.repository.ApiManager
import com.mystory.imagesearch.view.adapter.SearchDataSource
import com.mystory.imagesearch.view.adapter.SearchRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import com.mystory.imagesearch.Config
import com.mystory.imagesearch.repository.ApiManager.requestImageSearch
import rx.Observable

/**
 * MainActViewModel
 * @author wsseo
 * @since 2019. 3. 17
 **/
class MainActViewModel : BaseViewModel(){
    var progressShow = ObservableField<Boolean>()
    var query = MutableLiveData<String>()
    var time:Long = 0
    init {
        progressShow.set(false)
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        time = System.currentTimeMillis()
        Observable.just(System.currentTimeMillis())
            .delay(1, TimeUnit.SECONDS)
            .takeWhile{
                System.currentTimeMillis() - time >= 1000
            }
            .subscribe {
                Observable.just(s)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { searchText ->
                        searchText?.let {
                            if(it.isNotEmpty()) {
                                progressShow.set(true)
                                query.value = it.toString()
                            }
                        }
                    }
            }

    }
}