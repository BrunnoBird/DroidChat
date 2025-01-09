package com.bgabird.droidchat.data.network

import com.bgabird.droidchat.data.network.model.AuthRequest
import com.bgabird.droidchat.data.network.model.CreateAccountRequest
import com.bgabird.droidchat.data.network.model.TokenResponse

interface NetworkDataSource {

    suspend fun signUp(request: CreateAccountRequest)

    suspend fun signIn(request: AuthRequest): TokenResponse
}