package com.brainx.utils_extensions

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val isAndroid: Boolean = true
    override val isIOS: Boolean = false
    override val platform: String = "android"

}
actual fun getPlatform(): Platform = AndroidPlatform()