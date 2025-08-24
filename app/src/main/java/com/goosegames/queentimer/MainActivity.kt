package com.goosegames.queentimer

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.goosegames.queentimer.ui.pages.LoginPage
import com.goosegames.queentimer.ui.pages.PreferencesPage
import kotlinx.serialization.Serializable

@Serializable
object Login
@Serializable
object PreferencesInfo

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "credentials")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController: NavHostController = rememberNavController()
            NavHost(navController = navController, startDestination = Login)
            {
                composable<Login> { LoginPage(dataStore, navController) }
                composable<PreferencesInfo> { PreferencesPage(navController) }
            }

        }
    }
}

