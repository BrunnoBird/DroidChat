package com.bgabird.droidchat.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.bgabird.droidchat.ui.extension.getVisualTransformationForPassword
import com.bgabird.droidchat.ui.extension.isPassword
import com.bgabird.droidchat.ui.theme.DroidChatTheme

@Composable
fun SecondaryTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    extraText: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next
) {
    var inputText by remember {
        mutableStateOf(value)
    }

    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    BasicTextField(
        value = inputText,
        onValueChange = {
            inputText = it
            onValueChange(it)
        },
        modifier = modifier
            .fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            fontWeight = FontWeight.Bold
        ),
        keyboardOptions = KeyboardOptions(
            capitalization = if (keyboardType == KeyboardType.Text) {
                KeyboardCapitalization.Sentences
            } else KeyboardCapitalization.None,
            keyboardType = keyboardType,
            imeAction = imeAction,
        ),
        singleLine = true,
        maxLines = 1,
        visualTransformation = keyboardType.getVisualTransformationForPassword(
            visibility = passwordVisibility,
            keyboardType.isPassword()
        )
    ) { innerTextField ->
        innerTextField()
    }
}

@Preview(showBackground = true)
@Composable
private fun SecondaryTextFieldEmailPreview() {
    DroidChatTheme {
        SecondaryTextField(
            label = "E-mail",
            value = "",
            onValueChange = {},
            keyboardType = KeyboardType.Email
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SecondaryTextFieldPasswordPreview() {
    DroidChatTheme {
        SecondaryTextField(
            label = "Password",
            value = "",
            onValueChange = {},
            extraText = "Password matches",
            keyboardType = KeyboardType.Password
        )
    }
}