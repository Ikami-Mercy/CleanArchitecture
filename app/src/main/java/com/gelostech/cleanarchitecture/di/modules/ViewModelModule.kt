package com.gelostech.cleanarchitecture.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.gelostech.cleanarchitecture.di.ViewModelFactory
import com.gelostech.cleanarchitecture.di.ViewModelKey
import com.gelostech.cleanarchitecture.ui.viewmodels.PostsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    internal abstract fun postsViewModel(viewmodel: PostsViewModel): ViewModel

}