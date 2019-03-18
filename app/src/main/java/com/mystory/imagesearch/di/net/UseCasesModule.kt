package com.mystory.imagesearch.di.net

import com.mystory.imagesearch.domain.ImageSearchListInteractor
import com.mystory.imagesearch.domain.ImageSearchListUseCases
import com.mystory.imagesearch.model.ApiRepository
import dagger.Module
import dagger.Provides

/**
 * UseCasesModule
 * @author wsseo
 * @since 2019. 3. 14
 **/
@Module
class UseCasesModule {
    @Provides
    fun providesImageSearchListUseCases(apiRepository: ApiRepository): ImageSearchListUseCases = ImageSearchListInteractor(apiRepository)
}