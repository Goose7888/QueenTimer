package com.goosegames.queentimer.network

import com.goosegames.queentimer.network.ApiClient.client
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import com.goosegames.queentimer.models.LoginRequest
import io.ktor.client.request.get

object ApiRepository {
    // xApiKey seems to be required for every request
    const val X_API_KEY = "8b320c1fd5d5f5a4bf1bd95c7b18c61b"
    // appHeader seems to be required for some requests
    const val APP_HEADER = "CUSTOMER_APP_SPEED_QUEEN"

    suspend fun authLogin(email: String, password: String) = client.post(ApiRoutes.LOGIN) {
        header("x-api-key", X_API_KEY)
        header("app", APP_HEADER)
        header("ContentType", "application/json")

        setBody(LoginRequest(email = email, password = password))
    }

    suspend fun authStoredLogin(allianceAuthToken: String, userId: String) = client.get("${ApiRoutes.STORED_LOGIN}/$userId?include=all") {
        header("x-api-key", X_API_KEY)
        header("app", APP_HEADER)
        header("ContentType", "application/json")
        header("alliancels-auth-token", allianceAuthToken)
    }
}
