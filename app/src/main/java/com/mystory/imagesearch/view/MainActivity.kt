package com.mystory.imagesearch.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mystory.imagesearch.view.adapter.SearchRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.mystory.imagesearch.databinding.ActivityMainBinding
import com.mystory.imagesearch.view.viewmodel.MainActViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.mystory.imagesearch.data.document
import com.mystory.imagesearch.data.searchData
import com.mystory.imagesearch.view.adapter.SearchDataSource
import android.widget.EditText
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService




/**
 * mainactivity
 * @author wsseo
 * @since 2019. 3. 17
 **/
class MainActivity : AppCompatActivity() {
    var adapter = SearchRecyclerViewAdapter()
    var model:MainActViewModel? = null
    var searchDataSource: SearchDataSource = SearchDataSource()
    var obversable : io.reactivex.Observable<PagedList<document>>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = ViewModelProviders.of(this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return MainActViewModel(adapter) as T
                }
            }).get(MainActViewModel::class.java)

        val binding = DataBindingUtil.setContentView(this@MainActivity, com.mystory.imagesearch.R.layout.activity_main) as ActivityMainBinding
        binding.model = model

        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(true)
            .build()

        val builder = RxPagedListBuilder<Int, document>(object: DataSource.Factory<Int, document>() {
            override fun create(): DataSource<Int, document> {
                return searchDataSource
            }
        }, config)

        obversable = builder.buildObservable()

        var staggeredGridLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        rv_search.adapter = adapter
        rv_search.layoutManager = staggeredGridLayoutManager

        model?.toastMsg?.observe(this@MainActivity, Observer { text ->
            text?.let {
                Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
            }
        })

        model?.searchLiveData?.observe(this@MainActivity, Observer { data ->
            hideKeyboard()
            searchDataSource.setData(data)
            obversable?.subscribe {
                adapter.submitList(it)
                model?.progressShow?.set(false)
            }
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
