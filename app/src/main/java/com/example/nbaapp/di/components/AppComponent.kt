package com.example.nbaapp.di.components

import com.example.nbaapp.NbaApplication
import com.example.nbaapp.di.modules.AppModule
import com.example.nbaapp.di.modules.BuildersModule
import com.example.nbaapp.di.modules.NetModule
import com.example.nbaapp.presentation.nba.NbaFragment
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, BuildersModule::class, AppModule::class, NetModule::class])
interface AppComponent{
    fun inject(app: NbaApplication)
}