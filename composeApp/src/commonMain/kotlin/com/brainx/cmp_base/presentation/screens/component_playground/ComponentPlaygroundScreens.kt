package com.brainx.cmp_base.presentation.screens.component_playground

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Calendar
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import basecmp.composeapp.generated.resources.Res
import basecmp.composeapp.generated.resources.email
import basecmp.composeapp.generated.resources.ic_search
import com.brainx.cmp_base.presentation.theme.AppDimens
import com.brainx.cmp_base.presentation.theme.AppTheme
import com.brainx.cmp_base.presentation.theme.colors.LocalAppTheme
import com.brainx.cmp_base.presentation.ui_components.button.CustomButton
import com.brainx.cmp_base.presentation.ui_components.button.defaultFullWidthButtonModifier
import com.brainx.cmp_base.presentation.ui_components.button.defaultWrapContentButtonModifier
import com.brainx.cmp_base.presentation.ui_components.text.CustomText
import com.brainx.cmp_base.presentation.ui_components.text.CustomTextToDisplay
import com.brainx.cmp_base.presentation.ui_components.text_fields.basic_text_field.CustomTextField
import com.brainx.cmp_base.presentation.ui_components.text_fields.underline_text_field.CustomBasicUnderlineTextField
import com.brainx.utils_extensions.constants.ExtConstants
import com.mohamedrejeb.calf.ui.datepicker.rememberAdaptiveDatePickerState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource

private val ScreenPadding = 16.dp

@OptIn(ExperimentalResourceApi::class)
private val labelEmail: StringResource = Res.string.email

@Composable
fun ComponentButtonsScreen() {
    val appTheme = LocalAppTheme.current

    var isLoading by remember { mutableStateOf(false) }
    var clickCount by remember { mutableStateOf(0) }

    AppTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(appTheme.background.backgroundColor)
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(appTheme.background.backgroundColor)
                    .padding(paddingValues)
                    .padding(ScreenPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CustomText(
                    modifier = Modifier.fillMaxWidth(),
                    text = CustomTextToDisplay.StringText("Buttons playground"),
                    fontSize = AppDimens.Fonts.font20,
                    color = appTheme.textView.primaryBlackTextColor
                )

                CustomButton(
                    modifier = Modifier.defaultFullWidthButtonModifier(),
                    buttonText = CustomTextToDisplay.StringText("Primary button (clicks: $clickCount)"),
                    buttonColor = appTheme.button.primaryColor,
                    textColor = appTheme.button.secondaryWhiteTextColor,
                    showLoader = isLoading,
                    onClickAction = {
                        clickCount++
                        isLoading = !isLoading
                    }
                )

                CustomButton(
                    modifier = Modifier.defaultWrapContentButtonModifier(),
                    buttonText = CustomTextToDisplay.StringText("Gradient background"),
                    buttonBrush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFE3F2FD),
                            Color(0xFFBBDEFB),
                            Color(0xFF90CAF9)
                        )
                    ),
                    textColor = Color.Black,
                    onClickAction = { }
                )

                CustomButton(
                    modifier = Modifier.defaultFullWidthButtonModifier(),
                    buttonText = CustomTextToDisplay.StringText("Gradient border"),
                    buttonColor = appTheme.background.whiteColor,
                    textColor = appTheme.textView.primaryBlackTextColor,
                    borderBrush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFBBDEFB),
                            Color(0xFF90CAF9),
                            Color(0xFF64B5F6)
                        )
                    ),
                    borderWidth = 2.dp,
                    onClickAction = { }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentPickersScreen() {
    val appTheme = LocalAppTheme.current

    var composeSelectedDateMillis by remember { mutableStateOf<Long?>(null) }
    var composeSelectedHourMinute by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    var showComposeDatePicker by remember { mutableStateOf(false) }
    var showComposeTimePicker by remember { mutableStateOf(false) }

    var nativeSelectedDateMillis by remember { mutableStateOf<Long?>(null) }
    var nativeSelectedHourMinute by remember { mutableStateOf<Pair<Int, Int>?>(null) }

    AppTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(appTheme.background.backgroundColor)
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(appTheme.background.backgroundColor)
                    .padding(paddingValues)
                    .padding(ScreenPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CustomText(
                    modifier = Modifier.fillMaxWidth(),
                    text = CustomTextToDisplay.StringText("Pickers playground"),
                    fontSize = AppDimens.Fonts.font20,
                    color = appTheme.textView.primaryBlackTextColor
                )

                // region — Pure Compose (Material3) pickers

                CustomText(
                    modifier = Modifier.fillMaxWidth(),
                    text = CustomTextToDisplay.StringText("Pure Compose (Material3) pickers"),
                    fontSize = AppDimens.Fonts.font16,
                    color = appTheme.textView.secondaryGreyTextColor
                )

                val composeDateText = composeSelectedDateMillis?.toString() ?: "No date selected"
                CustomText(
                    modifier = Modifier.fillMaxWidth(),
                    text = CustomTextToDisplay.StringText("Selected date: $composeDateText"),
                    fontSize = AppDimens.Fonts.font16,
                    color = appTheme.textView.primaryBlackTextColor
                )

                CustomButton(
                    modifier = Modifier.defaultFullWidthButtonModifier(),
                    buttonText = CustomTextToDisplay.StringText("Open Compose DatePicker"),
                    buttonColor = appTheme.button.primaryColor,
                    textColor = appTheme.button.secondaryWhiteTextColor,
                    onClickAction = { showComposeDatePicker = true }
                )


                val composeTimeText = composeSelectedHourMinute?.let { (h, m) ->
                    val hh = h.toString().padStart(2, '0')
                    val mm = m.toString().padStart(2, '0')
                    "$hh:$mm"
                } ?: "No time selected"

                CustomText(
                    modifier = Modifier.fillMaxWidth(),
                    text = CustomTextToDisplay.StringText("Selected time: $composeTimeText"),
                    fontSize = AppDimens.Fonts.font16,
                    color = appTheme.textView.primaryBlackTextColor
                )

                CustomButton(
                    modifier = Modifier.defaultFullWidthButtonModifier(),
                    buttonText = CustomTextToDisplay.StringText("Open Compose TimePicker"),
                    buttonColor = appTheme.button.primaryColor,
                    textColor = appTheme.button.secondaryWhiteTextColor,
                    onClickAction = { showComposeTimePicker = true }
                )

                // endregion

                // region — Native pickers via expect/actual

                CustomText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    text = CustomTextToDisplay.StringText("Native pickers (expect/actual)"),
                    fontSize = AppDimens.Fonts.font16,
                    color = appTheme.textView.secondaryGreyTextColor
                )

                // endregion
            }

            if (showComposeDatePicker) {
                ComposeDatePickerDialog(
                    initialDateMillis = composeSelectedDateMillis,
                    onDateSelected = {
                        composeSelectedDateMillis = it
                        showComposeDatePicker = false
                    },
                    onDismiss = { showComposeDatePicker = false }
                )
            }

            if (showComposeTimePicker) {
                ComposeTimePickerDialog(
                    initialHour = composeSelectedHourMinute?.first,
                    initialMinute = composeSelectedHourMinute?.second,
                    onTimeSelected = { hour, minute ->
                        composeSelectedHourMinute = hour to minute
                        showComposeTimePicker = false
                    },
                    onDismiss = { showComposeTimePicker = false }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ComposeDatePickerDialog(
    initialDateMillis: Long?,
    onDateSelected: (Long) -> Unit,
    onDismiss: () -> Unit
) {
    val state = rememberDatePickerState(initialSelectedDateMillis = initialDateMillis)

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    val millis = state.selectedDateMillis
                    if (millis != null) {
                        onDateSelected(millis)
                    }
                    onDismiss()
                }
            ) { Text("OK") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = state)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ComposeTimePickerDialog(
    initialHour: Int?,
    initialMinute: Int?,
    onTimeSelected: (Int, Int) -> Unit,
    onDismiss: () -> Unit
) {
    val state = rememberTimePickerState(
        initialHour = initialHour ?: 12,
        initialMinute = initialMinute ?: 0,
        is24Hour = true
    )

    val timePickerState = rememberAdaptiveDatePickerState()


    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onTimeSelected(state.hour, state.minute)
                    onDismiss()
                }
            ) { Text("OK") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        text = {
            TimePicker(state = state)
        }
    )
}

@Composable
fun ComponentTextFieldsScreen() {
    val appTheme = LocalAppTheme.current

    var searchText by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    AppTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(appTheme.background.backgroundColor)
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(appTheme.background.backgroundColor)
                    .padding(paddingValues)
                    .padding(ScreenPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CustomText(
                    modifier = Modifier.fillMaxWidth(),
                    text = CustomTextToDisplay.StringText("Filled text fields"),
                    fontSize = AppDimens.Fonts.font20,
                    color = appTheme.textView.primaryBlackTextColor
                )

                CustomTextField(
                    modifier = Modifier.fillMaxWidth().height(60.dp),
                    text = searchText,
                    contentPaddingStart = AppDimens.Padding.padding8,
                    contentPaddingEnd = AppDimens.Padding.padding8,
                    placeHolderText = "Search movies",
                    leadingIcon = {
                        Image(
                            painter = painterResource(Res.drawable.ic_search),
                            contentDescription = ExtConstants.StringConstants.EMPTY
                        )
                    },
                    backgroundBrush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFE3F2FD),
                            Color(0xFFBBDEFB),
                            Color(0xFF90CAF9)
                        )
                    ),
                    onValueChange = { searchText = it }
                )

                CustomTextField(
                    modifier = Modifier.fillMaxWidth().height(60.dp),
                    text = password,
                    placeHolderText = "Password",
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    backgroundBrush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFE3F2FD),
                            Color(0xFFBBDEFB),
                            Color(0xFF90CAF9)
                        )
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = { password = it }
                )
            }
        }
    }
}

@Composable
fun ComponentUnderlineTextFieldsScreen() {
    val appTheme = LocalAppTheme.current

    var email by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val isEmailValid = remember(email) {
        email.contains("@") && email.contains(".")
    }

    AppTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(appTheme.background.backgroundColor)
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(appTheme.background.backgroundColor)
                    .padding(paddingValues)
                    .padding(ScreenPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CustomText(
                    modifier = Modifier.fillMaxWidth(),
                    text = CustomTextToDisplay.StringText("Underline text fields"),
                    fontSize = AppDimens.Fonts.font20,
                    color = appTheme.textView.primaryBlackTextColor
                )

                CustomBasicUnderlineTextField(
                    text = email,
                    label = labelEmail,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    isValid = isEmailValid,
                    supportText = if (isEmailValid) {
                        CustomTextToDisplay.StringText("Valid email")
                    } else {
                        CustomTextToDisplay.StringText("Enter a valid email address")
                    },
                    onValueChange = { email = it }
                )

            }
        }
    }
}

@Composable
fun ComponentTextScreen() {
    val appTheme = LocalAppTheme.current

    var useGradient by remember { mutableStateOf(true) }

    AppTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(appTheme.background.backgroundColor)
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(appTheme.background.backgroundColor)
                    .padding(paddingValues)
                    .padding(ScreenPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (useGradient) {
                        CustomText(
                            modifier = Modifier.fillMaxWidth(),
                            text = CustomTextToDisplay.StringText("Gradient text"),
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFFE53935),
                                    Color(0xFF1E88E5)
                                )
                            ),
                            fontSize = AppDimens.Fonts.font24
                        )
                    } else {
                        CustomText(
                            modifier = Modifier.fillMaxWidth(),
                            text = CustomTextToDisplay.StringText("Solid text"),
                            color = appTheme.textView.blueTextColor,
                            fontSize = AppDimens.Fonts.font24
                        )
                    }

                    CustomButton(
                        modifier = Modifier.defaultWrapContentButtonModifier(),
                        buttonText = CustomTextToDisplay.StringText("Toggle style"),
                        buttonColor = appTheme.button.primaryColor,
                        textColor = appTheme.button.secondaryWhiteTextColor,
                        onClickAction = { useGradient = !useGradient }
                    )
                }
            }
        }
    }
}

