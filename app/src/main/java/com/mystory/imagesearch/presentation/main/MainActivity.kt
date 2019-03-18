package com.mystory.imagesearch.presentation.main

import android.content.Context
import android.os.Bundle
import com.mystory.imagesearch.presentation.adapter.SearchRecyclerViewAdapter
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.mystory.imagesearch.databinding.ActivityMainBinding
import com.mystory.imagesearch.presentation.viewmodel.MainActViewModel
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.mystory.imagesearch.Config
import com.mystory.imagesearch.R
import com.mystory.imagesearch.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
/**
 * mainactivity
 * @author wsseo
 * @since 2019. 3. 17
 **/
class MainActivity : BaseActivity() {
    @Inject lateinit var viewModel: MainActViewModel
    var adapter = SearchRecyclerViewAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        screenComponent.inject(this)
        binding.model = viewModel

        var staggeredGridLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        rv_search.adapter = adapter
        rv_search.layoutManager = staggeredGridLayoutManager

        viewModel.pagedList?.observe(this@MainActivity, Observer {
           pagedList -> adapter.submitList(pagedList)
           hideKeyboard()
        })
        viewModel.state?.observe(this@MainActivity, Observer {
            state -> state?.let {
                var msg:String? = null
                when(it){
                    Config.STATE_SEARCH_NO_RESULT -> msg = "검색결과가 없습니다."
                    Config.STATE_SEARCH_QUERY_EMPTY -> msg = "검색어를 입력해주세요."
                    Config.STATE_SEARCH_ERROR -> msg = "오류가 발생하였습니다."
                    Config.STATE_SEARCH_COMPLETE_NORMAL -> viewModel.progressShow.set(false)
                }
                msg?.let { Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()   }
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
