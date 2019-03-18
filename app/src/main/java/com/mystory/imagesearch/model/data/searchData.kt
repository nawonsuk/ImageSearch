package com.mystory.imagesearch.data

import com.google.gson.annotations.SerializedName

/**
 * 이미지 검색 정보 데이타 클래스
 * @author wsseo
 * @since 2019. 3. 14.
 **/
data class searchData(@SerializedName("meta")var meta:meta?= null,
                      @SerializedName("documents")var documents:List<document>?=null)