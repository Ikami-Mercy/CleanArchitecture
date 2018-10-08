package com.gelostech.cleanarchitecture.di

import android.app.Application
import android.os.Build
import com.gelostech.cleanarchitecture.MyApplication
import com.gelostech.cleanarchitecture.di.modules.*
import com.gelostech.cleanarchitecture.ui.activities.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RoomModule::class, ViewModelModule::class, ActivitiesModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun roomModule(roomModule: RoomModule): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): AppComponent
    }

    fun inject(app: MyApplication)

}