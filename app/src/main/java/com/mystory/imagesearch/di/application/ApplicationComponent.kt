package com.mystory.imagesearch.di.application

import com.mystory.imagesearch.ImageSearchApplication
import com.mystory.imagesearch.di.net.NetModule
import com.mystory.imagesearch.di.net.RepositoryModule
import com.mystory.imagesearch.di.net.UseCasesModule
import com.mystory.imagesearch.di.screen.ScreenComponent
import com.mystory.imagesearch.di.screen.ScreenModule
import dagger.Component
import javax.inject.Singleton

/**
 * ApplicationComponent
 * @author wsseo
 * @since 2019. 3. 14
 **/
@Singleton
@Component(modules = arrayOf(
    ApplicationModule::class,
    NetModule::class,
    RepositoryModule::class,
    UseCasesModule::class
))
interface ApplicationComponent {

  fun inject(activity: ImageSearchApplication)

  fun plus(screenModule: ScreenModule): ScreenComponent
}
