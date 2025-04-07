package com.example.data.di

import com.example.data.datasource.local.history.database.AppDatabase
import com.example.data.datasource.local.history.database.HistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideHistoryDao(appDatabase: AppDatabase): HistoryDao = appDatabase.historyDao()

}