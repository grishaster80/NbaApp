package com.example.nbaapp.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nbaapp.data.model.Player
import io.reactivex.Single


@Dao
interface NbaDao {

    @Query("SELECT * FROM players")
    fun loadAllPlayers(): Single<List<Player>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayer(player:Player)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPlayers(players: List<Player>)
}