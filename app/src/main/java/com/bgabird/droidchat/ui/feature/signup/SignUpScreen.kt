package com.bgabird.droidchat.ui.feature.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bgabird.droidchat.R
import com.bgabird.droidchat.ui.component.PrimaryButton
import com.bgabird.droidchat.ui.component.ProfilePictureOptionsModalBottomSheet
import com.bgabird.droidchat.ui.component.ProfilePictureSelection
import com.bgabird.droidchat.ui.component.SecondaryTextField
import com.bgabird.droidchat.ui.theme.BackgroundGradient
import com.bgabird.droidchat.ui.theme.DroidChatTheme
import kotlinx.coroutines.launch

@Composable
fun SignUpRoute(
    viewModel: SignUpViewModel = viewModel()
) {
    val formState = viewModel.formState

    SignUpScreen(
        formState = formState,
        onFormEvent = viewModel::onFormEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignUpScreen(
    formState: SignUpFormState,
    onFormEvent: (SignUpFormEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .background(brush = BackgroundGradient)
            .verticalScroll(state = rememberScrollState())
            .imePadding(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(56.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.height(30.dp))

            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomStart = CornerSize(0.dp),
                    bottomEnd = CornerSize(0.dp)
                )
            ) {

                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfilePictureSelection(
                        imageUri = formState.profilePictureUri,
                        modifier = Modifier
                            .clickable {
                                onFormEvent(SignUpFormEvent.OpenProfilePictureOptionsModalBottomSheet)
                            }
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    val firstNameLabel = stringResource(id = R.string.feature_sign_up_first_name)
                    SecondaryTextField(
                        label = firstNameLabel,
                        value = formState.firstName,
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.FirstNameChanged(it))
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    val lastNameLabel = stringResource(id = R.string.feature_sign_up_last_name)
                    SecondaryTextField(
                        label = lastNameLabel,
                        value = formState.lastName,
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.LastNameChanged(it))
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    val emailLabel = stringResource(id = R.string.feature_sign_up_email)
                    SecondaryTextField(
                        label = emailLabel,
                        value = formState.email,
                        keyboardType = KeyboardType.Email,
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.EmailChanged(it))
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_password),
                        value = formState.password,
                        keyboardType = KeyboardType.Password,
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.PasswordChanged(it))
                        }
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_password_confirmation),
                        value = formState.passwordConfirmation,
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password,
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.PasswordConfirmationChanged(it))
                        }
                    )

                    Spacer(modifier = Modifier.height(36.dp))

                    PrimaryButton(
                        text = stringResource(id = R.string.feature_sign_up_button),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onFormEvent(SignUpFormEvent.Submit)
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            val sheetState = rememberModalBottomSheetState()
            val scope = rememberCoroutineScope()
            if (formState.isProfilePictureModalBottomSheetOpen) {
                ProfilePictureOptionsModalBottomSheet(
                    onPictureSelected = { uri ->
                        onFormEvent(SignUpFormEvent.ProfilePhotoUriChanged(uri))
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onFormEvent(SignUpFormEvent.CloseProfilePictureOptionsModalBottomSheet)
                            }
                        }
                    },
                    onDismissRequest = {
                        onFormEvent(SignUpFormEvent.CloseProfilePictureOptionsModalBottomSheet)
                    },
                    sheetState = sheetState
                )
            }
        }
    }
}

@Preview
@Composable
private fun SignUpRouteScreenPreview() {
    DroidChatTheme {
        SignUpScreen(
            formState = SignUpFormState(),
            onFormEvent = {}
        )
    }
}