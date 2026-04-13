package com.example.ktorsample.data.remote

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

internal actual fun provideHttpEngine(): HttpClientEngine = OkHttp.create()
