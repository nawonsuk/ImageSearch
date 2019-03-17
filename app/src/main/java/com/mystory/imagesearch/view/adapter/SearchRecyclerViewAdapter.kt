package com.mystory.imagesearch.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mystory.imagesearch.BR
import com.mystory.imagesearch.data.document
import com.mystory.imagesearch.databinding.LayoutImagelistBinding
import com.mystory.imagesearch.view.viewmodel.SearchListViewModel

/**
 * search recyclerview adapter
 * @author wsseo
 * @since 2019. 3. 17
 **/
class SearchRecyclerViewAdapter : PagedListAdapter<document, SearchRecyclerViewAdapter.SearchViewHolder>(diffCallback) {
    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<document>() {
            override fun areItemsTheSame(oldItem: document, newItem: document): Boolean =
                oldItem.image_url == newItem.image_url && oldItem.collection == newItem.collection
                        && oldItem.thumbnail_url == newItem.thumbnail_url
            override fun areContentsTheSame(oldItem: document, newItem: document): Boolean =
                oldItem.image_url == newItem.image_url && oldItem.collection == newItem.collection
                        && oldItem.thumbnail_url == newItem.thumbnail_url
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        var binding = LayoutImagelistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
    class SearchViewHolder(binding: LayoutImagelistBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding = binding
        fun bindTo(data:document?){
            data?.let { binding.setVariable(BR.model, SearchListViewModel(it.image_url)) }
        }
    }
}