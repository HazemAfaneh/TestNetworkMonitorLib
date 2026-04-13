package com.example.ktorsample

import androidx.compose.ui.window.ComposeUIViewController
import com.example.ktorsample.presentation.SampleScreen
import io.github.hazemafaneh.networkinspectionpro.internal.ui.NetworkInspectorOverlay

fun MainViewController() = ComposeUIViewController {
    NetworkInspectorOverlay {
        SampleScreen()
    }
}
