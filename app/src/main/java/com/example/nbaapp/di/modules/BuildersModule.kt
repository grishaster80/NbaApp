package com.example.nbaapp.di.modules

import com.example.nbaapp.presentation.nba.NbaActivity
import com.example.nbaapp.presentation.nba.NbaFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeNbaFragment(): NbaFragment
}