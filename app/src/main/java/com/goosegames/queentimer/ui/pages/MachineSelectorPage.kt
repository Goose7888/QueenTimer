package com.goosegames.queentimer.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavController
import com.example.queentimer.ui.theme.QueenTimerTheme
import com.goosegames.queentimer.ui.elements.ClearDataStore
import com.goosegames.queentimer.ui.elements.MainNavigationBar
import com.goosegames.queentimer.ui.elements.MainTopBar
import com.goosegames.queentimer.ui.elements.Page

@Composable
fun MachineSelectorPage(dataStore: DataStore<Preferences>, navController: NavController) {
    QueenTimerTheme {
        Scaffold(
            topBar = { MainTopBar(dataStore, navController) },
            bottomBar = { MainNavigationBar(navController, Page.MACHINE_SELECTOR) }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                ClearDataStore(dataStore)
            }
        }
    }
}