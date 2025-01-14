package com.bgabird.droidchat.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bgabird.droidchat.navigation.ChatNavHost

@Composable
fun ChatApp() {
    Scaffold(
        bottomBar = {
            //TODO
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            ChatNavHost()
        }
    }
}