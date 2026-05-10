package com.brainx.ticket_tribe

interface Platform {
    val name: String
    val isAndroid:Boolean
    val isIOS:Boolean
}

expect fun getPlatform(): Platform