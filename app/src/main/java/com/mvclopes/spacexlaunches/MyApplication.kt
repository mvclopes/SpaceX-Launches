package com.mvclopes.spacexlaunches

import android.app.Application
import com.mvclopes.spacexlaunches.di.spaceXModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(spaceXModule)
        }
    }
}
