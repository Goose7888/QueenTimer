package com.goosegames.queentimer.models

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.serialization.Serializable

object Globals {
    var userGuid: String? = null
    var allianceAuthToken: String? = null
    var user: User = User()
    object Pages {
        @Serializable
        object Loading
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

    }
}