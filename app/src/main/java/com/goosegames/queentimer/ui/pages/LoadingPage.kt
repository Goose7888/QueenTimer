package com.goosegames.queentimer.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavController
import com.goosegames.queentimer.models.Globals
import com.goosegames.queentimer.network.ApiRepository
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.timeout
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration.Companion.milliseconds

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoadingPage(dataStore: DataStore<Preferences>, navController: NavController) {
    val scope = rememberCoroutineScope()
    Scaffold { innerPadding ->

        Text(
            modifier = Modifier.padding(paddingValues = innerPadding),
            text = "Loading!! Please wait!! Thanks!!"
        )

        scope.launch {
            // If creds are good, go go in the app
            // If not, make them log in
            if (initialLogin(dataStore, navController)) {
                navController.navigate(Globals.Pages.MachineSelector)
            } else {
                navController.navigate(Globals.Pages.Login)
            }
        }
    }
}

// Returns: skipLogin: true if login page was skipped
suspend fun initialLogin(dataStore: DataStore<Preferences>, navController: NavController): Boolean {
    // sets vars in globals to a not null value if successful
    tryRetrieveData(dataStore)

    if (Globals.userGuid == null || Globals.allianceAuthToken == null) {
        return false
    }

    val httpRes: HttpResponse? = ApiRepository.authStoredLogin(allianceAuthToken = Globals.allianceAuthToken.toString(), userId = Globals.userGuid.toString())

    if (httpRes?.status?.value == 200) {
        Globals.user = httpRes.body()
        return true
    }

    return false
}

@OptIn(FlowPreview::class)
suspend fun tryRetrieveData(dataStore: DataStore<Preferences>) {
    val userGuidKey: Preferences.Key<String> = stringPreferencesKey("user_guid")
    val allianceAuthTokenKey: Preferences.Key<String> =
        stringPreferencesKey("alliance_auth_token")

    // Define flows to get dataStore values from
    val userGuidFlow: Flow<String?> = dataStore.data
        .map { credentials ->
            credentials[userGuidKey]
        }
    val authTokenFlow: Flow<String?> = dataStore.data
        .map { credentials ->
            credentials[allianceAuthTokenKey]
        }

    // Store dataStore values in Globals.xxx
    // WHY DO THESE FLOWS NEVER RESOLVE THEMSELVES???
    // This made me crash out for 4 hours
    val getUserGuid: Job = CoroutineScope(context = Dispatchers.IO).launch {
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
                Globals.userGuid = value
                value != null
            }
    }

    val getAuthToken: Job = CoroutineScope(context = Dispatchers.IO).launch {
        Globals.allianceAuthToken = authTokenFlow
            .timeout(100.milliseconds)
            .catch { exception ->
                if (exception is TimeoutCancellationException) {
                    emit(null)
                } else {
                    throw exception
                }
            }
            .firstOrNull { value ->
                Globals.allianceAuthToken = value
                value != null
            }
    }

    getUserGuid.join()
    getAuthToken.join()
}
