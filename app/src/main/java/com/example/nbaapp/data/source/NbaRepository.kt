package com.example.nbaapp.data.source

import com.example.nbaapp.data.model.Player
import com.example.nbaapp.data.source.local.NbaDao
import com.example.nbaapp.data.source.remote.ApiInterface
import com.example.nbaapp.utils.Utils
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class NbaRepository @Inject constructor(private val apiInterface: ApiInterface,
    private val nbaDao: NbaDao, val utils: Utils
) {



    fun getNbaPlayers():Observable<List<Player>>{
        val hasConnection = utils.isConnectedToInternet()
        var observableFromApi: Observable<List<Player>>? = null
        if(hasConnection){
            observableFromApi = getPlayersFromApi()
        }

        val observableFromDb = getPlayersFromDb()

        return if (hasConnection) Observable.concatArrayEager(observableFromApi,observableFromDb)
        else observableFromDb

    }

    private fun getPlayersFromApi(): Observable<List<Player>> {
        return apiInterface.getPlayers()
            .flatMapObservable {
                return@flatMapObservable Observable.fromArray(it.players)
            }
            .doOnNext {
                nbaDao.insertAllPlayers(it)
            }
    }


    private fun getPlayersFromDb():Observable<List<Player>>{
        return nbaDao.loadAllPlayers()
            .toObservable()
    }
}