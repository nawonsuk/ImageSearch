package com.mystory.imagesearch.presentation.main

import android.content.Context
import android.os.Bundle
import com.mystory.imagesearch.presentation.adapter.SearchRecyclerViewAdapter
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.mystory.imagesearch.databinding.ActivityMainBinding
import com.mystory.imagesearch.presentation.viewmodel.MainActViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.mystory.imagesearch.data.document
import com.mystory.imagesearch.presentation.adapter.SearchDataSource
import android.view.inputmethod.InputMethodManager
import com.mystory.imagesearch.Config
import com.mystory.imagesearch.R
import com.mystory.imagesearch.presentation.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


/**
 * mainactivity
 * @author wsseo
 * @since 2019. 3. 17
 **/
class MainActivity : BaseActivity() {
    //@Inject
    //lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var viewModel: MainActViewModel
    var searchDataSource:SearchDataSource? = null//imageSearchListUseCase.getSearchListBy("")
    var adapter = SearchRecyclerViewAdapter()
    ///var model:MainActViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActViewModel::class.java)
    var obversable : io.reactivex.Observable<PagedList<document>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        screenComponent.inject(this)
        binding.model = viewModel

        var config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(Config.PAGE_SIZE)
            .setPageSize(Config.PAGE_SIZE)
            .setPrefetchDistance(Config.PAGE_SIZE/2)
            .setEnablePlaceholders(true)
            .build()

        var builder = RxPagedListBuilder<Int, document>(object: DataSource.Factory<Int, document>() {
            override fun create(): DataSource<Int, document>? {
                return searchDataSource
            }
        }, config)

        obversable = builder.buildObservable()

        var staggeredGridLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        rv_search.adapter = adapter
        rv_search.layoutManager = staggeredGridLayoutManager

        viewModel?.query?.observe(this@MainActivity, Observer { text ->
            text?.let { searchTxt->
                searchDataSource?.searchQuery(searchTxt)
                obversable?.subscribe {
                    adapter.submitList(it)
                    viewModel?.progressShow?.set(false)
                }
            }
        })
        searchDataSource?.toastMsg?.observe(this@MainActivity, Observer { text ->
            text?.let {
                Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
            }
        })
        searchDataSource?.searchData?.observe(this@MainActivity, Observer { data ->
            hideKeyboard()
        })
    }

    fun hideKeyboard(){
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(et_search.windowToken, 0)
        }catch(e:Exception){

        }
    }
}
