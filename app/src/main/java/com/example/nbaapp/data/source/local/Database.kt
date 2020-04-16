package com.example.nbaapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nbaapp.data.model.Player


@Database(entities = [Player::class], version = 2)
abstract class Database : RoomDatabase() {
    abstract fun nbaDao():NbaDao
}