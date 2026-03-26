package com.brainx.ticket_tribe.utils.enums

import org.jetbrains.compose.resources.StringResource
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.choose_from_gallery
import tickettribecmp.composeapp.generated.resources.take_new_photo

enum class ImagePickerOptions(val textToDisplay: StringResource) {
    Gallery(textToDisplay = Res.string.take_new_photo),
    Camera(textToDisplay = Res.string.choose_from_gallery)
}