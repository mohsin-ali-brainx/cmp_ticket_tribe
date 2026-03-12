package com.brainx.utils_extensions

import android.os.Build
import android.util.Log

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val isAndroid: Boolean = true
    override val isIOS: Boolean = false
    override val platform: String = "android"

}
actual fun getPlatform(): Platform = AndroidPlatform()

actual fun platformLog(tag: String, message: String) {
    Log.d(tag, message)
}