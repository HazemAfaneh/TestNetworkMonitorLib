package com.jetbrain.testnetworkmonitorlib

import androidx.compose.ui.window.ComposeUIViewController
import io.github.hazemafaneh.networkinspectionpro.internal.ui.NetworkInspectorOverlay

fun MainViewController() = ComposeUIViewController {
    NetworkInspectorOverlay {
        App()
    }
}