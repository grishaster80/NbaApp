package com.example.nbaapp.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class ApiResponse(
    @Json(name = "data")
    val players: List<Player>,
    @Json(name = "meta")
    val meta: Meta
)