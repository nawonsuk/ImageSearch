package com.mystory.imagesearch.data

import com.google.gson.annotations.SerializedName

/**
 * 이미지 검색 meta 데이타 클래스
 * @author wsseo
 * @since 2019. 3. 14.
 **/
data class meta(@SerializedName("total_count")var total_count:Int=0,
                @SerializedName("pageable_count") var pageable_count:Int=0,
                @SerializedName("is_end") var is_end:Boolean=false)