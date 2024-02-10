package com.example.decomposetest

import android.app.Application
import android.util.Log
import com.example.decomposetest.di.dataModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(dataModule)
        }
    }
}