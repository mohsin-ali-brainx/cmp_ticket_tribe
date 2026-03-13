package com.brainx.utils_extensions

import androidx.compose.runtime.Composable

/**
 * Duration options for toast-like messages.
 */
enum class ToastDurationType {
    SHORT,
    LONG
}

// composeApp/src/commonMain/kotlin/your_package_name/ToastManager.kt
expect open class ToastManager() {
    fun showToast(message: String, toastDurationType: ToastDurationType)
}

