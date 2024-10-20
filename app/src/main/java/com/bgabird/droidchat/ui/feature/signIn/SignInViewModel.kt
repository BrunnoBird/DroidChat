package com.bgabird.droidchat.ui.feature.signIn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bgabird.droidchat.ui.validator.FormValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class SignInViewModel(
    private val formValidator: FormValidator<SignInFormState>
) : ViewModel() {

    var formState by mutableStateOf(SignInFormState())
        private set

    fun onFormEvent(event: SignInFormEvent) {
        when (event) {
            is SignInFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email, emailError = null)
            }

            is SignInFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password, passwordError = null)
            }

            is SignInFormEvent.Submit -> doSignIn()
        }
    }

    private fun doSignIn() {
        if (isFormValid()) {
            formState = formState.copy(isLoading = true)
            viewModelScope.launch {
                delay(3000)
                formState = formState.copy(isLoading = false)
            }
        }

        //request api
    }

    private fun isFormValid(): Boolean {
        return !formValidator.validate(formState).also {
            formState = it
        }.hasError
    }
}