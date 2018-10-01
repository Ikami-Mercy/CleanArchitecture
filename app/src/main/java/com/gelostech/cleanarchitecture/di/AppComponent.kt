package com.gelostech.cleanarchitecture.di

import com.gelostech.cleanarchitecture.di.modules.AppModule
import com.gelostech.cleanarchitecture.di.modules.NetworkModule
import com.gelostech.cleanarchitecture.di.modules.RoomModule
import com.gelostech.cleanarchitecture.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetworkModule::class, RoomModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}