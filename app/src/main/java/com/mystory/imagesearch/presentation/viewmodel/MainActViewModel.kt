package com.mystory.imagesearch.presentation.viewmodel

import androidx.databinding.ObservableField
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rx.Observable
import javax.inject.Inject

/**
 * MainActViewModel
 * @author wsseo
 * @since 2019. 3. 17
 **/
class MainActViewModel @Inject constructor() : ViewModel(){
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