package com.mystory.imagesearch.di.application

import com.mystory.imagesearch.ImageSearchApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: ImageSearchApplication) {
  @Provides
  @Singleton
  fun provideApplication(): ImageSearchApplication {
    return application
  }
}
