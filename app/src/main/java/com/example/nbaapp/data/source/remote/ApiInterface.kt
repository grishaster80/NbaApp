package com.example.nbaapp.data.source.remote

import com.example.nbaapp.data.model.ApiResponse
import com.example.nbaapp.data.model.Player
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {

    @GET("players")
    fun getPlayers(): Single<ApiResponse>
}