package com.jetbrain.testnetworkmonitorlib.data.remote

import io.github.hazemafaneh.networkinspectionpro.NetworkInspectorPlugin
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal expect fun provideHttpEngine(): HttpClientEngine

fun createHttpClient(): HttpClient = HttpClient(provideHttpEngine()) {
    install(NetworkInspectorPlugin)
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }
}
