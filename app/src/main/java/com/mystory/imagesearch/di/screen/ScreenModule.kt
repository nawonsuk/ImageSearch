package com.mystory.imagesearch.di.screen
import com.mystory.imagesearch.di.scope.PerScreen
import com.mystory.imagesearch.presentation.BaseActivity
import dagger.Module
import dagger.Provides

/**
 * ScreenModule
 * @author wsseo
 * @since 2019. 3. 14
 **/
@Module
class ScreenModule(private val activity: BaseActivity) {

  @PerScreen
  @Provides
  fun providesActivity(): BaseActivity {
    return activity
  }
}
