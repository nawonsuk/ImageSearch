package com.mystory.imagesearch.di.screen

import com.mystory.imagesearch.di.scope.PerScreen
import com.mystory.imagesearch.presentation.main.MainActivity
import dagger.Subcomponent

/**
 * ScreenComponent
 * @author wsseo
 * @since 2019. 3. 14
 **/
@PerScreen
@Subcomponent(modules = [ScreenModule::class])
interface ScreenComponent {
  fun inject(mainActivity: MainActivity)
}
