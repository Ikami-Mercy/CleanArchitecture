package com.gelostech.cleanarchitecture.di

import android.app.Application
import com.gelostech.cleanarchitecture.MyApplication
import com.gelostech.cleanarchitecture.di.modules.*
import com.gelostech.cleanarchitecture.ui.activities.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RoomModule::class, ViewModelModule::class, ActivitiesModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application)

        fun build(): Application
    }

    fun inject(app: MyApplication)

}