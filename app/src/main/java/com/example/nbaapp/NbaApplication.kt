package com.example.nbaapp

import android.app.Activity
import android.app.Application
import com.example.nbaapp.di.components.AppComponent
import com.example.nbaapp.di.components.DaggerAppComponent
import com.example.nbaapp.di.modules.AppModule
import com.example.nbaapp.di.modules.NetModule
import com.example.nbaapp.utils.Constants
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class NbaApplication:Application(), HasAndroidInjector{
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>


    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule(Constants.URL))
            .build().inject(this)


    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector


}