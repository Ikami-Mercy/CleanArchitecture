package com.gelostech.cleanarchitecture.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.gelostech.cleanarchitecture.MyApplication
import com.gelostech.cleanarchitecture.di.Injectable
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

object AppInjector {

    private fun AppInjector() {}

    fun init(application: MyApplication) {
        DaggerAppComponent.Builder()
                .application(application)
                .appModule(application)
                .build()
                .inject(application)

        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
                handleActivity(p0!!)
            }

            override fun onActivityPaused(p0: Activity?) {

            }

            override fun onActivityResumed(p0: Activity?) {

            }

            override fun onActivityStarted(p0: Activity?) {

            }

            override fun onActivityDestroyed(p0: Activity?) {

            }

            override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {

            }

            override fun onActivityStopped(p0: Activity?) {

            }
        })
    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasSupportFragmentInjector) {
            AndroidInjection.inject(activity)
        }

        if (activity is FragmentActivity) {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {

                    if (f is Injectable) {
                        AndroidSupportInjection.inject(f)
                    }

                }
            }, true)
        }
    }

}