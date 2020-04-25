package com.example.nbaapp.presentation.nba

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nbaapp.data.model.Player
import com.example.nbaapp.data.source.NbaRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NbaViewModel @Inject constructor(
    private val nbaRepository: NbaRepository):ViewModel(){

    var playersResult: MutableLiveData<List<Player>> = MutableLiveData()
    var playersError: MutableLiveData<String> = MutableLiveData()
    var playersLoader: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var disposableObserver: DisposableObserver<List<Player>>


    fun playersResult():MutableLiveData<List<Player>>{
        return playersResult
    }

    fun playersError():MutableLiveData<String>{
        return playersError
    }

    fun playersLoader():MutableLiveData<Boolean>{
        return playersLoader
    }


    fun loadPlayers(){
        disposableObserver = object : DisposableObserver<List<Player>>(){
            override fun onNext(players: List<Player>) {
                playersResult.postValue(players)
                playersLoader.postValue(false)
            }
            override fun onError(e: Throwable) {
                playersError.postValue(e.message)
                playersLoader.postValue(false)
            }
            override fun onComplete() {
            }
        }

        nbaRepository.getNbaPlayers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(disposableObserver)

    }

    fun disposeElements(){
        if(!disposableObserver.isDisposed) disposableObserver.dispose()
    }


}