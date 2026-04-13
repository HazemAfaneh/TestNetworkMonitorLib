package com.jetbrain.testnetworkmonitorlib.di

import com.jetbrain.testnetworkmonitorlib.data.remote.ApiService
import com.jetbrain.testnetworkmonitorlib.data.remote.createHttpClient
import com.jetbrain.testnetworkmonitorlib.presentation.SampleViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    single { createHttpClient() }
    single { ApiService(get()) }
    single { SampleViewModel(get()) }
}

/**
 * iOS entry point — call from Swift's App.init().
 * Android uses [KtorSampleApp] which calls startKoin with androidContext.
 */
fun initKoin() {
    startKoin {
        modules(appModule)
    }
}
