package com.bgabird.droidchat.ui.feature.signIn

import androidx.annotation.StringRes

data class SignInFormState(
    val email: String = "",
    val password: String = "",
    @StringRes
    val emailError: Int? = null,
    @StringRes
    val passwordError: Int? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false
)
