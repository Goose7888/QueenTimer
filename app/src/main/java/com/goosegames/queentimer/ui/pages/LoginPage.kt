package com.goosegames.queentimer.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.goosegames.queentimer.ui.elements.LoginForm
import com.goosegames.queentimer.ui.elements.NewAccountButton

@Composable
fun LoginPage(modifier: Modifier, dataStore: DataStore<Preferences>) {
    Column(
        modifier = modifier
    ) {
        LoginForm(dataStore)
        NewAccountButton()
    }
}