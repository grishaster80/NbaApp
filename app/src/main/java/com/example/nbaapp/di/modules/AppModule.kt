package com.example.nbaapp.di.modules

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.nbaapp.data.source.local.Database
import com.example.nbaapp.data.source.local.NbaDao
import com.example.nbaapp.utils.Constants
import com.example.nbaapp.utils.Utils
import com.example.nbaapp.viewmodel.NbaViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideNbaDatabase(app: Application): Database = Room.databaseBuilder(app,
            Database::class.java, Constants.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideNbaDao(
        database: Database): NbaDao = database.nbaDao()

    @Provides
    @Singleton
    fun provideNbaViewModelFactory(
        factory: NbaViewModelFactory
    ): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideUtils(): Utils = Utils(app)
}