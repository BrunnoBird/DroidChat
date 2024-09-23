package com.bgabird.droidchat.ui.feature.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bgabird.droidchat.R
import com.bgabird.droidchat.ui.component.PrimaryButton
import com.bgabird.droidchat.ui.component.PrimaryChatTextField
import com.bgabird.droidchat.ui.theme.BackgroundGradient
import com.bgabird.droidchat.ui.theme.DroidChatTheme

@Composable
fun SignInRoute() {
    SignInScreen()
}

@Composable
fun SignInScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(brush = BackgroundGradient)
            .imePadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(78.dp))


        Column(
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_medium))
        ) {
            var email by remember { mutableStateOf("") }

            PrimaryChatTextField(
                value = email,
                onInputChange = {
                    email = it
                },
                placeholder = "Email",
                leftIcon = R.drawable.ic_envelope
            )

            Spacer(modifier = Modifier.height(16.dp))

            var password by remember { mutableStateOf("") }

            PrimaryChatTextField(
                value = password,
                onInputChange = {
                    password = it
                },
                placeholder = "Digite sua senha",
                leftIcon = R.drawable.ic_lock,
                keyboardType = KeyboardType.Password
            )

            Spacer(modifier = Modifier.height(98.dp))

            var isLoading by remember { mutableStateOf(false) }

            PrimaryButton(text = stringResource(id = R.string.feature_login_button),
                onClick = {
                    isLoading = !isLoading
                },
                isLoading = isLoading
            )
        }
    }
}

@Preview
@Composable
private fun SingInScreenPreview() {
    DroidChatTheme {
        SignInScreen()
    }
}