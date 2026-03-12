package com.brainx.utils_extensions

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val isAndroid: Boolean = false
    override val isIOS: Boolean = true
    override val platform: String = "ios"

}
actual fun getPlatform(): Platform = IOSPlatform()

actual fun platformLog(tag: String, message: String) {
    platform.Foundation.NSLog("[$tag] $message")
}