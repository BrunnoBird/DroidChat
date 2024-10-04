package com.bgabird.droidchat.ui.extension

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

fun KeyboardType.isPassword() = this == KeyboardType.Password

fun KeyboardType.getVisualTransformationForPassword(visibility: Boolean, isPassword: Boolean): VisualTransformation {
    return if (isPassword) {
        if (visibility) {
            VisualTransformation.None
        } else PasswordVisualTransformation()

    } else VisualTransformation.None
}