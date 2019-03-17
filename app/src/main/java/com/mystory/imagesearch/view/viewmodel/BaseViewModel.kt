package com.mystory.imagesearch.view.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.jakewharton.rxbinding2.widget.RxTextView
import com.mystory.imagesearch.data.document
import com.mystory.imagesearch.data.searchData
import com.mystory.imagesearch.repository.ApiManager
import com.mystory.imagesearch.view.adapter.SearchDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit
/**
 * BaseViewModel
 * @author wsseo
 * @since 2019. 3. 17
 **/
abstract class BaseViewModel : ViewModel(){
    var compositeDisposable  = CompositeDisposable()

    fun addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}