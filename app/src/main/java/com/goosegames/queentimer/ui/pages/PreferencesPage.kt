package com.goosegames.queentimer.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.queentimer.R
import com.example.queentimer.ui.theme.QueenTimerTheme
import kotlinx.serialization.Serializable


// Where to mess with user, region, code, building/room, sign out,
// theme menu (dark mode / dynamic colors)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferencesPage(navController: NavController)
{
    QueenTimerTheme {
        Scaffold(
            topBar = {
                TopAppBar (
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Small Top App Bar")
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
                                contentDescription = "Description"
                            )
                        }
                    }
                )
            }
        )
        { innerPadding ->
            Column(
                modifier = Modifier.padding(paddingValues = innerPadding)
            ) {
                MenuBar()
                Column(
                    Modifier
                        .verticalScroll(
                            state = rememberScrollState(),
                            enabled = true
                        )

                ) {
                    Text("World")
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuBar()
{
    // back button on top-left

}
