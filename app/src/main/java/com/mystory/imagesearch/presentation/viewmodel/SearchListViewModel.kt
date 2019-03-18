package com.mystory.imagesearch.presentation.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField

/**
 * SearchListViewModel
 * @author wsseo
 * @since 2019. 3. 17
 **/
class SearchListViewModel(image_url:String?) : BaseObservable(){
    var image_url = ObservableField<String>()
    init {
        this.image_url.set(image_url)
    }
}