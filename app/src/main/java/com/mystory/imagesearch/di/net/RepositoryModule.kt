package com.mystory.imagesearch.di.net

import com.mystory.imagesearch.model.ApiManager
import com.mystory.imagesearch.model.ApiRepository
import com.mystory.imagesearch.model.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * RepositoryModule
 * @author wsseo
 * @since 2019. 3. 14
 **/
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providesApiRepository(apiService: ApiService): ApiRepository = ApiManager(apiService)
}