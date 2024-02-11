package com.example.decomposetest

import android.app.Application
import com.example.decomposetest.di.dataModule
import com.example.decomposetest.di.domainKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(dataModule, domainKoinModule)
        }
    }
}