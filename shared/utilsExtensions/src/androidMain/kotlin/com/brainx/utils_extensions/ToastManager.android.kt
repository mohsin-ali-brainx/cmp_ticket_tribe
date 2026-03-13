package com.brainx.utils_extensions

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import android.app.Activity

private var activityProvider: () -> Activity? = {
    null
}

fun setActivityProvider(provider: () -> Activity?) {
    activityProvider = provider
}

/**
 * Android actual implementation of [ToastManager].
 */


actual open class ToastManager actual constructor() {
    actual fun showToast(message: String, toastDurationType: ToastDurationType) {
        val context = activityProvider.invoke()
        val duration = when (toastDurationType) {
            ToastDurationType.SHORT -> Toast.LENGTH_SHORT
            ToastDurationType.LONG -> Toast.LENGTH_LONG
        }
        Toast.makeText(context, message, duration).show()
    }
}

