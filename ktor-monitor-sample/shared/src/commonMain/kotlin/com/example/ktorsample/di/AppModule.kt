package com.example.ktorsample.di

import com.example.ktorsample.data.remote.ApiService
import com.example.ktorsample.data.remote.createHttpClient
import com.example.ktorsample.presentation.SampleViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    single { createHttpClient() }
    single { ApiService(get()) }
    single { SampleViewModel(get()) }
}

/**
 * Entry point for iOS — call this from Swift's App.init().
 * Android uses [KtorSampleApp] which calls startKoin directly with androidContext.
 */
fun initKoin() = startKoin {
    modules(appModule)
}
