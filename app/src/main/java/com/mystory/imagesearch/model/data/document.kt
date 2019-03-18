package com.mystory.imagesearch.data

import com.google.gson.annotations.SerializedName

/**
 * 이미지 검색 document 데이타 클래스
 * @author wsseo
 * @since 2019. 3. 14.
 **/
data class document(@SerializedName("collection")var collection:String?="",
                    @SerializedName("thumbnail_url") var thumbnail_url:String?="",
                    @SerializedName("image_url") var image_url:String?="",
                    @SerializedName("width") var width:String?="",
                    @SerializedName("height")var height:String?="",
                    @SerializedName("display_sitename") var display_sitename:String?="",
                    @SerializedName("doc_url") var doc_url:String?="",
                    @SerializedName("datetime") var datetime:String?="")