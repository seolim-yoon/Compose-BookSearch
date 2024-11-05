package com.example.data.di

import com.example.data.datasource.local.BookLocalDataSource
import com.example.data.datasource.local.BookLocalDataSourceImpl
import com.example.data.datasource.remote.BookRemoteDataSource
import com.example.data.datasource.remote.BookRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    @Singleton
    fun bindsBookLocalDataSource(bookLocalDataSourceImpl: BookLocalDataSourceImpl): BookLocalDataSource

    @Binds
    @Singleton
    fun bindsBookRemoteDataSource(bookRemoteDataSourceImpl: BookRemoteDataSourceImpl): BookRemoteDataSource
}