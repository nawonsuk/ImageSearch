package com.mystory.imagesearch.view.adapter

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
/**
 * SearchBindingAdapter
 * @author wsseo
 * @since 2019. 3. 17
 **/
object SearchBindingAdapter {
    @BindingAdapter("loadImage")
    @Synchronized @JvmStatic fun loadImage(iv:ImageView, url:String?) {
        try {
            url?.let {
                if(it.isNotEmpty()){
                   Picasso.with(iv.context).load(it).into(iv)
                }
            }
        }catch (e:Exception){
            Log.e("loadImage","loadImage Exception")
        }
    }
}