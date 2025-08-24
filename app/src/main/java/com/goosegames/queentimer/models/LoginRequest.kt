package com.goosegames.queentimer.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val littleDuck: String = "b3b3d23bbdd58fabprodCustomer_app_speed_queen", // Seems to be hard-coded in official app
    val password: String
)
