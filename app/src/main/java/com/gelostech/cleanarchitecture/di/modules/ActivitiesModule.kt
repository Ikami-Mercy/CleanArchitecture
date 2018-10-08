package com.gelostech.cleanarchitecture.di.modules

import com.gelostech.cleanarchitecture.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

}