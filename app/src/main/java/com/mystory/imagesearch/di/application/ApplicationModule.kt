package com.mystory.imagesearch.di.application

import com.mystory.imagesearch.ImageSearchApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * ApplicationModule
 * @author wsseo
 * @since 2019. 3. 14
 **/
@Module
class ApplicationModule(private val application: ImageSearchApplication) {
  @Provides
  @Singleton
  fun provideApplication(): ImageSearchApplication {
    return application
  }
}
