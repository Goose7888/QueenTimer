package com.goosegames.queentimer.ui.elements

import android.content.Context
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
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.goosegames.queentimer.models.User
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import network.ApiRepository

@Composable
fun LoginForm(dataStore: DataStore<Preferences>) {
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
                    if (httpRes.status.isSuccess()) {
                        val user: User = httpRes.body()
                        val userGuid = stringPreferencesKey("user_guid")
                        val allianceAuthToken = stringPreferencesKey("alliance_auth_token")
                        dataStore.edit { credentials ->
                            credentials[userGuid] = user.data?.id.toString()
                            credentials[allianceAuthToken] =
                                httpRes.headers["alliancels-auth-token"] as String
                        }
                    }
                }
            }
        ) {
            Text("Login")
        }

        DebugCard(jsonStr = displayString)
    }
}

@Composable
fun NewAccountButton()
{
    Button(
        onClick = {

        }
    ) {
        Text("New Account")
    }
}
