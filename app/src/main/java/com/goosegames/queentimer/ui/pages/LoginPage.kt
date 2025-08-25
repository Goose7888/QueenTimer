package com.goosegames.queentimer.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavController
import com.example.queentimer.ui.theme.QueenTimerTheme
import com.goosegames.queentimer.MachineSelector
import com.goosegames.queentimer.ui.elements.LoginForm


@Composable
fun LoginPage(dataStore: DataStore<Preferences>, navController: NavController) {
    QueenTimerTheme {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                LoginForm(dataStore)
                DebugButton(navController)
            }
        }
    }
}

@Composable
fun DebugButton(navController: NavController)
{
    Scaffold { innerPadding ->
        Button(
            modifier = Modifier.padding(paddingValues = innerPadding),
            onClick = {
                navController.navigate(route = MachineSelector)
            }
        ) {
            Text("Debug Button")
        }
    }
}