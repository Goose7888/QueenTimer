package com.goosegames.queentimer.ui.elements

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.queentimer.R
import com.goosegames.queentimer.LocationSelector
import com.goosegames.queentimer.Login
import com.goosegames.queentimer.MachineSelector
import com.goosegames.queentimer.Options
import com.goosegames.queentimer.TimerManager
import com.goosegames.queentimer.ui.pages.LocationSelectorPage

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
                navController.navigate(LocationSelector)
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
                navController.navigate(MachineSelector)
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
                navController.navigate(TimerManager)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(navController: NavController) {
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
                    navController.navigate(
                        route = Login,
                        navOptions =  navOptions {
                            popUpTo<MachineSelector> { saveState = true }
                            restoreState = true
                        }
                    )
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
                    navController.navigate(Options)
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
