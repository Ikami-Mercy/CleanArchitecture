package com.gelostech.cleanarchitecture

import android.support.multidex.MultiDexApplication
import timber.log.Timber

class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }

}