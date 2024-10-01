package com.bgabird.droidchat.ui.feature.signIn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    var formState by mutableStateOf(SignInFormState())
        private set

    fun onFormEvent(event: SignInFormEvent) {
        when (event) {
            is SignInFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
            }

            is SignInFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
            }

            is SignInFormEvent.Submit -> doSignIn()
        }
    }

    private fun doSignIn() {
        formState = formState.copy(isLoading = true)
        //request api
    }
}