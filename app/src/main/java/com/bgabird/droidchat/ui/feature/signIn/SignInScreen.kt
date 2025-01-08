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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bgabird.droidchat.R
import com.bgabird.droidchat.ui.component.PrimaryButton
import com.bgabird.droidchat.ui.component.PrimaryTextField
import com.bgabird.droidchat.ui.theme.BackgroundGradient
import com.bgabird.droidchat.ui.theme.DroidChatTheme

@Composable
fun SignInRoute(
    viewModel: SignInViewModel = viewModel {
        SignInViewModel(
            formValidator = SignInFormValidator()
        )
    },
    navigateToSignUp: () -> Unit

) {
    val formState = viewModel.formState
    SignInScreen(
        formState,
        onFormEvent = viewModel::onFormEvent,
        onRegisterClick = navigateToSignUp,
    )
}

@Composable
fun SignInScreen(
    formState: SignInFormState,
    onFormEvent: (SignInFormEvent) -> Unit,
    onRegisterClick: () -> Unit,
) {
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
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_medium)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            PrimaryTextField(
                value = formState.email,
                onInputChange = {
                    onFormEvent(SignInFormEvent.EmailChanged(it))
                },
                placeholder = "Email",
                leftIcon = R.drawable.ic_envelope,
                keyboardType = KeyboardType.Email,
                errorMessage = formState.emailError?.let {
                    stringResource(id = it)
                },
                hasHelperText = false
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryTextField(
                value = formState.password,
                onInputChange = {
                    onFormEvent(SignInFormEvent.PasswordChanged(it))
                },
                placeholder = "Digite sua senha",
                leftIcon = R.drawable.ic_lock,
                keyboardType = KeyboardType.Password,
                errorMessage = formState.passwordError?.let {
                    stringResource(id = it)
                },
                hasHelperText = false
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (formState.hasError) {
                Text(
                    text = "E-mail ou senha inválido",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(98.dp))

            PrimaryButton(
                text = stringResource(id = R.string.feature_login_button),
                onClick = {
                    onFormEvent(SignInFormEvent.Submit)
                },
                isLoading = formState.isLoading
            )

            Spacer(modifier = Modifier.height(62.dp))

            val noAccountText = stringResource(id = R.string.feature_login_no_account)
            val registerText = stringResource(id = R.string.feature_login_register)
            val noAccountRegisterText = "$noAccountText $registerText"
            val annotatedString = buildAnnotatedString {
                val registerTextStartIndex = noAccountRegisterText.indexOf(registerText)
                val registerTextEndIndex = registerTextStartIndex + registerText.length

                append(noAccountRegisterText)

                addStyle(
                    style = SpanStyle(
                        color = Color.White
                    ),
                    start = 0,
                    end = registerTextStartIndex
                )

                addLink(
                    clickable = LinkAnnotation.Clickable(
                        tag = "register_text",
                        styles = TextLinkStyles(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                textDecoration = TextDecoration.Underline
                            )
                        ),
                        linkInteractionListener = {
                            onRegisterClick()
                        }
                    ),
                    start = registerTextStartIndex,
                    end = registerTextEndIndex
                )
            }

            Text(text = annotatedString)
        }
    }
}

@Preview
@Composable
private fun SingInScreenPreview() {
    DroidChatTheme {
        SignInScreen(
            formState = SignInFormState(),
            onFormEvent = {},
            onRegisterClick = {}
        )
    }
}