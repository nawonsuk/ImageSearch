package com.mystory.imagesearch.presentation.viewmodel

import androidx.databinding.ObservableField
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.mystory.imagesearch.Config
import com.mystory.imagesearch.data.document
import com.mystory.imagesearch.domain.ImageSearchListUseCases
import com.mystory.imagesearch.presentation.adapter.SearchDataSource
import rx.Observable
import javax.inject.Inject

/**
 * MainActViewModel
 * @author wsseo
 * @since 2019. 3. 17
 **/
class MainActViewModel @Inject constructor(imageSearchListUseCases: ImageSearchListUseCases) : ViewModel(){
    var obversable : io.reactivex.Observable<PagedList<document>>? = null
    var progressShow = ObservableField<Boolean>()
    var pagedList = MutableLiveData<PagedList<document>>()
    var state = MutableLiveData<Int>()
    var searchDataSource: SearchDataSource = imageSearchListUseCases.getSearchDataSource("", state)
    var time:Long = 0
    init {
        progressShow.set(false)

        var config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(Config.PAGE_SIZE)
            .setPageSize(Config.PAGE_SIZE)
            .setPrefetchDistance(Config.PAGE_SIZE/2)
            .setEnablePlaceholders(false)
            .build()

        var builder = RxPagedListBuilder<Int, document>(object: DataSource.Factory<Int, document>() {
            override fun create(): DataSource<Int, document>? {
                return searchDataSource
            }
        }, config)

        obversable = builder.buildObservable()
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
                        searchText?.let { searchText->
                            if(searchText.isNotEmpty()) {
                                progressShow.set(true)
                                searchDataSource?.searchQuery(searchText.toString())
                                obversable?.subscribe {
                                    pagedList.value = it
                                }
                            } else {
                                state.value = Config.STATE_SEARCH_QUERY_EMPTY
                            }
                        }?:run{
                            state.value = Config.STATE_SEARCH_QUERY_EMPTY
                        }
                    }
            }

    }
}