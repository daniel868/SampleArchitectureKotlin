package com.example.kotlinmvvm_rxjava.di

import android.content.Context
import androidx.room.Room
import com.example.kotlinmvvm_rxjava.data.NewsLocalDataSource
import com.example.kotlinmvvm_rxjava.data.local.NewsDao
import com.example.kotlinmvvm_rxjava.data.local.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object LocalModule {
    
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "NewsDB"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideDao(newsDatabase: NewsDatabase): NewsDao =
        newsDatabase.newsDao()
}