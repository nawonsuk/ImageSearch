package com.mystory.imagesearch.di.application

import com.mystory.imagesearch.ImageSearchApplication
import com.mystory.imagesearch.di.screen.ScreenComponent
import com.mystory.imagesearch.di.screen.ScreenModule
import com.mystory.imagesearch.di.viewmodel.ViewModelFactoryModule
import com.mystory.imagesearch.di.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class
))
interface ApplicationComponent {

  fun inject(activity: ImageSearchApplication)

  fun plus(screenModule: ScreenModule): ScreenComponent
}
