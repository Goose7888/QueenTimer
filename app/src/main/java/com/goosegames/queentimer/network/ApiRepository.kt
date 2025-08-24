package network

import network.ApiClient.client
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import com.goosegames.queentimer.models.LoginRequest

class ApiRepository {
    // xApiKey seems to be required for every request
    val xApiKey = "8b320c1fd5d5f5a4bf1bd95c7b18c61b"
    // appHeader seems to be required for some requests
    val appHeader = "CUSTOMER_APP_SPEED_QUEEN"
    suspend fun authLogin(email: String, password: String) = client.post(ApiRoutes.LOGIN) {
        header("x-api-key", xApiKey)
        header("app", appHeader)
        headers {
            append(HttpHeaders.ContentType, "application/json")
        }
        setBody(LoginRequest(email = email, password = password))
    }

}