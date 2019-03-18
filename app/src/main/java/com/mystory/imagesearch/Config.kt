package com.mystory.imagesearch
/**
* config 클래스 - 환경설정 클래스
* @author 서원석
* @since 2019. 2. 8. AM 12:05
**/
object Config {
    val BASE_URL = "https://dapi.kakao.com"
    val PAGE_SIZE = 30

    const val STATE_SEARCH_NO_RESULT = 0
    const val STATE_SEARCH_ERROR = 1
    const val STATE_SEARCH_COMPLETE_NORMAL = 2
    const val STATE_SEARCH_QUERY_EMPTY = 3
}