package com.bgabird.droidchat.ui.feature.signIn

import com.bgabird.droidchat.R
import com.bgabird.droidchat.ui.validator.EmailValidator
import com.bgabird.droidchat.ui.validator.FormValidator
import com.bgabird.droidchat.ui.validator.PasswordValidator

class SignInFormValidator: FormValidator<SignInFormState> {
    override fun validate(formState: SignInFormState): SignInFormState {
        val isEmailValid = EmailValidator.isValid(formState.email)
        val isPasswordValid = PasswordValidator.isValid(formState.password)

        val hasError = listOf(
            isEmailValid,
            isPasswordValid
        ).any { !it }

        return formState.copy(
            emailError = if(!isEmailValid) R.string.error_message_email_invalid else null,
            passwordError = if (!isPasswordValid) R.string.error_message_password_invalid else null,
            hasError = hasError
        )
    }
}