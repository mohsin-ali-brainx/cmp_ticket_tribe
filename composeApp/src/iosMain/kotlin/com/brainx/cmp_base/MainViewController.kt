package com.brainx.cmp_base

import androidx.compose.ui.window.ComposeUIViewController
import com.brainx.cmp_base.di.initKoin


fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }