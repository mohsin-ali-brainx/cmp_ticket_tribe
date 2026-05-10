package com.brainx.utils_extensions

interface Platform {
    val name: String
    val isAndroid:Boolean
    val isIOS:Boolean
    val platform:String
}

expect fun getPlatform(): Platform

/**
 * Platform-specific logging. Use this instead of println() for logs that should appear
 * in Android Logcat and Xcode/Console.app on iOS.
 */
expect fun platformLog(tag: String, message: String)
