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
import com.goosegames.queentimer.models.Globals
import com.goosegames.queentimer.ui.pages.LoadingPage
import com.goosegames.queentimer.ui.pages.LocationSelectorPage
import com.goosegames.queentimer.ui.pages.LoginPage
import com.goosegames.queentimer.ui.pages.MachineSelectorPage
import com.goosegames.queentimer.ui.pages.OptionsPage
import com.goosegames.queentimer.ui.pages.TimerManagerPage



val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "credentials")


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController: NavHostController = rememberNavController()
            NavHost(navController = navController, startDestination = Globals.Pages.Loading)
            {
                composable<Globals.Pages.Loading> { LoadingPage(dataStore, navController) }
                composable<Globals.Pages.Login> { LoginPage(dataStore, navController) }
                composable<Globals.Pages.Options> { OptionsPage(dataStore, navController) }
                composable<Globals.Pages.LocationSelector> { LocationSelectorPage(dataStore, navController) }
                composable<Globals.Pages.MachineSelector> { MachineSelectorPage(dataStore, navController) }
                composable<Globals.Pages.TimerManager> { TimerManagerPage(dataStore, navController) }
            }
        }
    }
}

