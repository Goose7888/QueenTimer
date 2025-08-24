package com.goosegames.queentimer.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.goosegames.queentimer.models.User
import com.goosegames.queentimer.ui.elements.DebugCard
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import kotlinx.coroutines.launch
import network.ApiRepository

@Composable
fun LoginForm() {
    val scope = rememberCoroutineScope()
    val apiRepo = ApiRepository()
    var httpRes: HttpResponse
    val emailState: TextFieldState = rememberTextFieldState()
    val passwordState: TextFieldState = rememberTextFieldState()
    var displayString: String by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
    ) {
        TextField(
            state = emailState,
            label = { Text("Email") }

        )
        SecureTextField(
            state = passwordState,
            label = { Text("Password")}
        )
        Button(
            onClick = {
                scope.launch {
                    httpRes = apiRepo.authLogin(email = emailState.text.toString(), password = passwordState.text.toString())
                    displayString = httpRes.toString()
                    val user: User
                    // if 200, store into User object
                    if (httpRes.status.isSuccess()) {
                        displayString = httpRes.body()
                        user = httpRes.body()
                    }
                }
            }
        ) {
            Text("Login")
        }

        DebugCard(jsonStr = displayString)
    }
}