package com.goosegames.queentimer.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.queentimer.ui.theme.QueenTimerTheme
import com.goosegames.queentimer.ui.elements.MainNavigationBar
import com.goosegames.queentimer.ui.elements.MainTopBar
import com.goosegames.queentimer.ui.elements.Page

@Composable
fun TimerManagerPage(navController: NavController)
{
    QueenTimerTheme {
        Scaffold(
            topBar = {
                MainTopBar(navController)
            },
            bottomBar = {
                MainNavigationBar(navController, Page.TIMER_MANAGER)
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Text("Timer Manager")
            }
        }
    }
}
