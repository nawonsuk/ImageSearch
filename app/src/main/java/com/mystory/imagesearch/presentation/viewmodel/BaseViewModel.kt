package com.mystory.imagesearch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

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