package com.brainx.ticket_tribe.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.helvetica_neue_black
import tickettribecmp.composeapp.generated.resources.helvetica_neue_black_italic
import tickettribecmp.composeapp.generated.resources.helvetica_neue_bold
import tickettribecmp.composeapp.generated.resources.helvetica_neue_bold_italic
import tickettribecmp.composeapp.generated.resources.helvetica_neue_heavy
import tickettribecmp.composeapp.generated.resources.helvetica_neue_heavy_italic
import tickettribecmp.composeapp.generated.resources.helvetica_neue_italic
import tickettribecmp.composeapp.generated.resources.helvetica_neue_light
import tickettribecmp.composeapp.generated.resources.helvetica_neue_medium
import tickettribecmp.composeapp.generated.resources.helvetica_neue_medium_italic
import tickettribecmp.composeapp.generated.resources.helvetica_neue_roman
import tickettribecmp.composeapp.generated.resources.helvetica_neue_thin
import tickettribecmp.composeapp.generated.resources.helvetica_neue_thin_italic
import tickettribecmp.composeapp.generated.resources.helvetica_neue_ultra_light
import tickettribecmp.composeapp.generated.resources.helvetica_neue_ultra_light_italic


@Composable
private fun appFontFamily() = FontFamily(
    Font(Res.font.helvetica_neue_medium),
)

@Composable
fun appPrimaryFontFamily() = appFontFamily()

@Composable
fun appTypography() =
    Typography().run {
        val fontFamily = appPrimaryFontFamily()
        copy(
            headlineLarge = TextStyle(
                fontWeight = FontWeight.W500,
                fontFamily = fontFamily,
                fontSize = 38.sp,
            ),
            headlineMedium = TextStyle(
                fontWeight = FontWeight.W500,
                fontFamily = fontFamily,
                fontSize = 32.sp,
            ),
            headlineSmall = TextStyle(
                fontWeight = FontWeight.W500,
                fontFamily = fontFamily,
                fontSize = 26.sp,
            ),
            bodyLarge = TextStyle(
                fontWeight = FontWeight.W500,
                fontFamily = fontFamily,
                fontSize = 20.sp,
            ),
            bodyMedium = TextStyle(
                fontWeight = FontWeight.W400,
                fontFamily = fontFamily,
                fontSize = 18.sp,
            ),
            bodySmall = TextStyle(
                fontWeight = FontWeight.W400,
                fontFamily = fontFamily,
                fontSize = 16.sp,
            ),
            labelMedium = TextStyle(
                fontWeight = FontWeight.W400,
                fontFamily = fontFamily,
                fontSize = 14.sp,
            ),
            labelSmall = TextStyle(
                fontWeight = FontWeight.W400,
                fontFamily = fontFamily,
                fontSize = 12.sp,
            ),
        )
    }
