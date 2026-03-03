package com.brainx.cmp_base

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform