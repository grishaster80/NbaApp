package com.example.nbaapp.di.modules

import com.example.nbaapp.presentation.NbaActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeNbaActivity():NbaActivity
}