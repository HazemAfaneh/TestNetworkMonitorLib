package com.jetbrain.testnetworkmonitorlib

import android.app.Application
import com.jetbrain.testnetworkmonitorlib.di.appModule
import io.github.hazemafaneh.networkinspectionpro.NetworkInspectionPro
import io.github.hazemafaneh.networkinspectionpro.init
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KtorSampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
            NetworkInspectionPro.init(this)
        startKoin {
            androidContext(this@KtorSampleApp)
            modules(appModule)
        }
    }
}
