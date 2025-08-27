package com.goosegames.queentimer.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavController
import com.example.queentimer.ui.theme.QueenTimerTheme
import com.goosegames.queentimer.ui.elements.OptionsBar


// Where to mess with user, sign out,
// theme menu (dark mode / dynamic colors)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionsPage(dataStore: DataStore<Preferences>, navController: NavController)
{
    QueenTimerTheme {
        Scaffold(
            topBar = {
                OptionsBar(navController)
            }
        )
        { innerPadding ->
            Column(
                modifier = Modifier.padding(paddingValues = innerPadding)
            ) {
                Column(
                    Modifier
                        .verticalScroll(
                            state = rememberScrollState(),
                            enabled = true
                        )
                ) {
                    Text("User Information:")
                }
            }
        }
    }
}
