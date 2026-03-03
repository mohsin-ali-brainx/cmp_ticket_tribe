package com.brainx.ui_components.text

import androidx.compose.ui.text.AnnotatedString
import org.jetbrains.compose.resources.StringResource
sealed interface DisplayText{
    data class StringResourceText(val text: StringResource):DisplayText
    data class StringText(val text:String):DisplayText
    data class AnnotatedStringText(val text: AnnotatedString):DisplayText
}