package com.example.nbaapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(
    tableName = "players"
)
data class Player(
    @Json(name = "id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "first_name")
    @Json(name = "first_name")
    val firstName: String?,

    @ColumnInfo(name = "height_feet")
    @Json(name = "height_feet")
    val heightFeet: Int?,

    @ColumnInfo(name = "height_inches")
    @Json(name = "height_inches")
    val heightInches: Int?,

    @ColumnInfo(name = "last_name")
    @Json(name ="last_name")
    val lastName: String?,

    @ColumnInfo(name = "position")
    @Json(name = "position")
    val position: String?,

    @ColumnInfo(name = "weight_pounds")
    @Json(name ="weight_pounds")
    val weightPounds: Int?
)