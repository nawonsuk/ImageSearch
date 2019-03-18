package com.mystory.imagesearch

import android.app.Application
import com.mystory.imagesearch.di.application.ApplicationComponent
import com.mystory.imagesearch.di.application.ApplicationModule
import com.mystory.imagesearch.di.application.DaggerApplicationComponent


class ImageSearchApplication  : Application() {
    lateinit var component: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        inject()
    }
    fun inject() {
        component = DaggerApplicationComponent.builder().applicationModule(
                ApplicationModule(this)).build()
        component.inject(this)
    }
}
