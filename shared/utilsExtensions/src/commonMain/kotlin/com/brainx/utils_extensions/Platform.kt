package com.brainx.utils_extensions

interface Platform {
    val name: String
    val isAndroid:Boolean
    val isIOS:Boolean
    val platform:String
}

expect fun getPlatform(): Platform