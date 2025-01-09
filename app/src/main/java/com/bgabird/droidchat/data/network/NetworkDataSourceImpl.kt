package com.bgabird.droidchat.data.network

import com.bgabird.droidchat.data.network.model.AuthRequest
import com.bgabird.droidchat.data.network.model.CreateAccountRequest
import com.bgabird.droidchat.data.network.model.TokenResponse

class NetworkDataSourceImpl: NetworkDataSource {
    //14:58
    override suspend fun signUp(request: CreateAccountRequest) {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(request: AuthRequest): TokenResponse {
        TODO("Not yet implemented")
    }
}