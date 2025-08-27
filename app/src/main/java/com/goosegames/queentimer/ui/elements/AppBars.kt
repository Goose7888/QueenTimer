package com.goosegames.queentimer.ui.elements

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.navigation.NavController
import com.example.queentimer.R
import com.goosegames.queentimer.models.Globals
import com.goosegames.queentimer.models.User
import kotlinx.coroutines.launch

enum class Page {
    LOCATION_SELECTOR,
    MACHINE_SELECTOR,
    TIMER_MANAGER,
    LOGIN,
    OPTIONS
}
@Composable
fun MainNavigationBar(navController: NavController, page: Page)
{
    NavigationBar(
        windowInsets = NavigationBarDefaults.windowInsets
    ) {
        // Location Selection
        NavigationBarItem(
            selected = (page == Page.LOCATION_SELECTOR),
            onClick = {
                if (page != Page.LOCATION_SELECTOR) {
                    navController.navigate((Globals.Pages.LocationSelector))
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.apartment_24px),
                    contentDescription = "Location Selector"
                )
            }
        )
        // Machine Selector / Timer Starter
        NavigationBarItem(
            selected = (page == Page.MACHINE_SELECTOR),
            onClick = {
                if (page != Page.MACHINE_SELECTOR) {
                    navController.navigate(Globals.Pages.MachineSelector)
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.local_laundry_service_24px),
                    contentDescription = "Laundry Machine Selector"
                )
            }
        )
        // Timer Management
        NavigationBarItem(
            selected = (page == Page.TIMER_MANAGER),
            onClick = {
                if (page != Page.TIMER_MANAGER) {
                    navController.navigate(Globals.Pages.TimerManager)
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.timer_24px),
                    contentDescription = "Timer Management"
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun MainTopBar(dataStore: DataStore<Preferences>, navController: NavController) {
    val scope = rememberCoroutineScope()
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(
                text = "Queen Timer",
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        var loggingOut: Boolean = true
                        // clear data store and user variable
                        Globals.user = User()
                        dataStore.edit { preferences ->
                            preferences.clear()
                        }

                        // Present user with fresh login screen
                        navController.navigate(
                            route = Globals.Pages.Login,
    //                        navOptions =  navOptions {
    //                            popUpTo<Login> {
    //                                inclusive = true
    //                                saveState = true
    //                            }
    //                            restoreState = true
                        )
                        loggingOut = false
                    }
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.logout_24px),
                    contentDescription = "Logout of account"
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(Globals.Pages.Options)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.settings_24px),
                    contentDescription = "Settings"
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionsBar(navController: NavController) {
    TopAppBar (
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
    ),
    title = {
        Text(
            text = "Settings",
            color = MaterialTheme.colorScheme.onBackground
        )
    },
    navigationIcon = {
        IconButton(
            onClick = {
                navController.navigateUp()
            }
        )
        {
            Icon(
                painter = painterResource(R.drawable.arrow_back_24px),
                contentDescription = "Back"
            )
        }
    }
)
}
