package com.mystory.imagesearch.di.viewmodel

import androidx.lifecycle.ViewModel
import com.mystory.imagesearch.presentation.viewmodel.MainActViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass


/**
 * Created by Antoni Castejón on 03/01/2018.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule{
    @Binds
    @IntoMap
    @ViewModelKey(MainActViewModel::class)
    abstract fun bindMainActViewModel(viewModel: MainActViewModel) : ViewModel
}