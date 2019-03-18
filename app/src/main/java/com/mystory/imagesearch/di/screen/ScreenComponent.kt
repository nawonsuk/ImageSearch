package com.mystory.imagesearch.di.screen

import com.mystory.imagesearch.di.scope.PerScreen
import com.mystory.imagesearch.presentation.main.MainActivity
import dagger.Subcomponent


@PerScreen
@Subcomponent(modules = [ScreenModule::class])
interface ScreenComponent {
  fun inject(mainActivity: MainActivity)
}
