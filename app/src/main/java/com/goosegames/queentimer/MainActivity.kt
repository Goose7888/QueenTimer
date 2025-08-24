package com.goosegames.queentimer

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.queentimer.ui.theme.QueenTimerTheme
import com.goosegames.queentimer.models.User
import com.goosegames.queentimer.ui.pages.LoginPage

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "credentials")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QueenTimerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginPage(dataStore = dataStore, modifier = Modifier.padding(paddingValues = innerPadding))
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginFormPreview() {
//    LoginPage(dataStore = dataStore, modifier = Modifier)
}

