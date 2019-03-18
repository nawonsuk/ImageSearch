package com.mystory.imagesearch.presentation

import androidx.appcompat.app.AppCompatActivity
import com.mystory.imagesearch.ImageSearchApplication
import com.mystory.imagesearch.di.application.ApplicationComponent
import com.mystory.imagesearch.di.screen.ScreenModule

open class BaseActivity : AppCompatActivity() {

  val screenComponent by lazy {
    getApplicationComponent().plus(ScreenModule(this))
  }

  private fun getApplicationComponent(): ApplicationComponent {
    return (application as ImageSearchApplication).component
  }
}