package com.goosegames.queentimer

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.goosegames.queentimer.models.User
import com.goosegames.queentimer.ui.pages.LocationSelectorPage
import com.goosegames.queentimer.ui.pages.LoginPage
import com.goosegames.queentimer.ui.pages.MachineSelectorPage
import com.goosegames.queentimer.ui.pages.PreferencesPage
import com.goosegames.queentimer.ui.pages.TimerManagerPage
import kotlinx.serialization.Serializable

@Serializable
object Login
@Serializable
object Options
@Serializable
object LocationSelector
@Serializable
object MachineSelector
@Serializable
object TimerManager

var user: User = User()

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
                composable<Options> { PreferencesPage(navController) }
                composable<MachineSelector> { MachineSelectorPage(navController) }
                composable<LocationSelector> { LocationSelectorPage(navController) }
                composable<MachineSelector> { MachineSelectorPage(navController) }
                composable<TimerManager> { TimerManagerPage(navController) }
            }

        }
    }
}

