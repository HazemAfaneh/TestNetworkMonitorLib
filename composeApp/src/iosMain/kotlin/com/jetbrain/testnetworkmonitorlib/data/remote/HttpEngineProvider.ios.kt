package com.jetbrain.testnetworkmonitorlib.data.remote

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

internal actual fun provideHttpEngine(): HttpClientEngine = Darwin.create()
