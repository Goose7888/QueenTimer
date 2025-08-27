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
            if (!initialLogin(dataStore, navController)) {
                navController.navigate(Globals.Pages.Login)
            }
        }
    }
}

// @Returns skipLogin: true if login page was skipped
@OptIn(FlowPreview::class)
suspend fun initialLogin(dataStore: DataStore<Preferences>, navController: NavController): Boolean {
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

    if (Globals.userGuid == null || Globals.allianceAuthToken == null) {
        return false
    }
    navController.navigate(Globals.Pages.MachineSelector)
    return true
}