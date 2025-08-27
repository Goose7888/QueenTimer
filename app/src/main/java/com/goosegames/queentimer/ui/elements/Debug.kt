package com.goosegames.queentimer.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.timeout
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun DebugCard(jsonStr: String) {
    val scrollState = rememberScrollState()

    Card(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(
            state = scrollState,
            enabled = true
        )
        .padding(all = 8.dp),
    ) {
        Text(
            modifier = Modifier.padding(start = 12.dp, top=12.dp),
            text = "response: ",
            style = MaterialTheme.typography.labelSmall,
            fontFamily = FontFamily.Monospace
        )
        Text(
            modifier = Modifier.padding(all = 12.dp),
            text = jsonStr,
            fontFamily = FontFamily.Monospace
        )
    }

}

@Composable
fun ClearDataStore(dataStore: DataStore<Preferences>) {
    val scope = rememberCoroutineScope()
    Button(
        onClick = {
            scope.launch {
                dataStore.edit { preferences ->
                    preferences.clear()
                }
                println("Cleared Datastore!")
            }
        }
    ) {
        Text("Clear DataStore!!")
    }
}

@Composable
fun DebugButton(navController: NavController, dataStore: DataStore<Preferences>) {
    val scope = rememberCoroutineScope()
    Button(
        onClick = {
            scope.launch {
                dataStore.edit { credentials ->
                    credentials[stringPreferencesKey("user_guid")] = "test_user_guid"
                    credentials[stringPreferencesKey("alliance_auth_token")] = "test_auth_token"
                }
                println("Items added to dataStore (Collected)")
            }
        }
    ) {
        Text("Debug Button")
    }
}

@OptIn(FlowPreview::class)
@Composable
fun DebugButton2(navController: NavController, dataStore: DataStore<Preferences>) {
    val scope = rememberCoroutineScope()
    Button(
        onClick = {
            scope.launch {
                val userGuidFlow: Flow<String?> = dataStore.data
                    .map { credentials ->
                        credentials[stringPreferencesKey("user_guid")]
                    }
                val authTokenFlow: Flow<String?> = dataStore.data
                    .map { credentials ->
                        credentials[stringPreferencesKey("alliance_auth_token")]
                    }

                CoroutineScope(context = Dispatchers.IO).launch {
                    userGuidFlow
                        .timeout(100.milliseconds)
                        .catch { exception ->
                            if (exception is TimeoutCancellationException) {
                                emit(null)
                            } else {
                                throw exception
                            }
                        }
                        .firstOrNull { value ->
                            println("User Guid: $value")
                            value != null
                        }

                }
                CoroutineScope(context = Dispatchers.IO).launch {
                    authTokenFlow
                        .timeout(100.milliseconds)
                        .catch { exception ->
                            if (exception is TimeoutCancellationException) {
                                emit(null)
                            } else {
                                throw exception
                            }
                        }
                        .firstOrNull { value ->
                            println("Auth Token: $value")
                            value != null
                        }
                }
            }
        }
    ) {
        Text("Print dataStore")
    }
}
