package com.goosegames.queentimer.network

object ApiRoutes {
    private const val BASE_URL:String = "https://api.alliancelslabs.com"
    var LOGIN = "$BASE_URL/auth/login"
    var STORED_LOGIN = "$BASE_URL/users"
}