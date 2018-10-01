package com.gelostech.cleanarchitecture

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.gelostech.cleanarchitecture.di.AppComponent
import com.gelostech.cleanarchitecture.di.DaggerAppComponent
import com.gelostech.cleanarchitecture.di.modules.AppModule
import com.gelostech.cleanarchitecture.di.modules.NetworkModule
import com.gelostech.cleanarchitecture.di.modules.RoomModule
import dagger.Component
import timber.log.Timber

class MyApplication : MultiDexApplication() {
    private lateinit var appComponent: AppComponent
    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        context = this
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .roomModule(RoomModule(this))
                .build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

}