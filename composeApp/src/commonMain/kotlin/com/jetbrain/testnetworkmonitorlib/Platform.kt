package com.jetbrain.testnetworkmonitorlib

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform