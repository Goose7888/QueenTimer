package com.goosegames.queentimer.models

import kotlinx.serialization.Serializable

@Serializable
data class JsonApi (
    val version: String
)