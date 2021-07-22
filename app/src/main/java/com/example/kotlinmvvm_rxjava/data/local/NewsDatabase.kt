package com.example.kotlinmvvm_rxjava.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlinmvvm_rxjava.data.entities.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}