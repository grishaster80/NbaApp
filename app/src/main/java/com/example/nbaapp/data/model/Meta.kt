package com.example.nbaapp.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Meta(
    @Json(name = "current_page")
    val currentPage: Int?,
    @Json(name = "next_page")
    val nextPage: Int?,
    @Json(name = "per_page")
    val perPage: Int?,
    @Json(name = "total_count")
    val totalCount: Int?,
    @Json(name = "total_pages")
    val totalPages: Int?
)