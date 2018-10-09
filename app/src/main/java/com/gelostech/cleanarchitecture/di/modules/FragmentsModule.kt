package com.gelostech.cleanarchitecture.di.modules

import com.gelostech.cleanarchitecture.ui.fragments.PostsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributesFragmentsModule(): PostsFragment

}