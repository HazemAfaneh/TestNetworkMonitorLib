package com.example.ktorsample

import android.app.Application
import com.example.ktorsample.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KtorSampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KtorSampleApp)
            modules(appModule)
        }
    }
}
