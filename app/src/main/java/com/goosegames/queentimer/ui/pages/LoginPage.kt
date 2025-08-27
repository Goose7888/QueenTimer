package com.goosegames.queentimer.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavController
import com.example.queentimer.ui.theme.QueenTimerTheme
import com.goosegames.queentimer.ui.elements.ClearDataStore
import com.goosegames.queentimer.ui.elements.DebugButton
import com.goosegames.queentimer.ui.elements.DebugButton2
import com.goosegames.queentimer.ui.elements.LoginForm



@Composable
fun LoginPage(dataStore: DataStore<Preferences>, navController: NavController) {
    QueenTimerTheme {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                LoginForm(dataStore, navController)
                Row {
                    DebugButton(navController, dataStore)
                    DebugButton2(navController, dataStore)
                    ClearDataStore(dataStore)
                }
            }
        }
    }
}





