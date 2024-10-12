package com.bgabird.droidchat.ui.feature.signup

import android.net.Uri
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bgabird.droidchat.R
import com.bgabird.droidchat.ui.component.PrimaryButton
import com.bgabird.droidchat.ui.component.ProfilePictureOptionsModalBottomSheet
import com.bgabird.droidchat.ui.component.ProfilePictureSelection
import com.bgabird.droidchat.ui.component.SecondaryTextField
import com.bgabird.droidchat.ui.theme.BackgroundGradient
import com.bgabird.droidchat.ui.theme.DroidChatTheme
import kotlinx.coroutines.launch

@Composable
fun SignUpRoute() {
    SignUpScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignUpScreen() {
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

            var profilePictureSelectedUri by remember {
                mutableStateOf<Uri?>(null)
            }

            var openProfilePictureOptionsModalBottomSheet by remember {
                mutableStateOf(false)
            }

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
                        imageUri = profilePictureSelectedUri,
                        modifier = Modifier
                            .clickable {
                                openProfilePictureOptionsModalBottomSheet = true
                            }
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    val firstNameLabel = stringResource(id = R.string.feature_sign_up_first_name)
                    SecondaryTextField(
                        label = firstNameLabel,
                        value = "",
                        onValueChange = {}
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    val lastNameLabel = stringResource(id = R.string.feature_sign_up_last_name)
                    SecondaryTextField(
                        label = lastNameLabel,
                        value = "",
                        onValueChange = {}
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    val emailLabel = stringResource(id = R.string.feature_sign_up_email)
                    SecondaryTextField(
                        label = emailLabel,
                        value = "",
                        keyboardType = KeyboardType.Email,
                        onValueChange = {}
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_password),
                        value = "",
                        keyboardType = KeyboardType.Password,
                        onValueChange = {}
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_password_confirmation),
                        value = "",
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password,
                        onValueChange = {}
                    )

                    Spacer(modifier = Modifier.height(36.dp))

                    PrimaryButton(
                        text = stringResource(id = R.string.feature_sign_up_button),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {}
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            val sheetState = rememberModalBottomSheetState()
            val scope = rememberCoroutineScope()
            if (openProfilePictureOptionsModalBottomSheet) {
                ProfilePictureOptionsModalBottomSheet(
                    onPictureSelected = { uri ->
                        profilePictureSelectedUri = uri
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                openProfilePictureOptionsModalBottomSheet = false
                            }
                        }
                    },
                    onDismissRequest = {
                        openProfilePictureOptionsModalBottomSheet = false
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
        SignUpScreen()
    }
}