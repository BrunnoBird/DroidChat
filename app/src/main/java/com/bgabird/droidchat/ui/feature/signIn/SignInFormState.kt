package com.bgabird.droidchat.ui.feature.signIn

data class SignInFormState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false
)
