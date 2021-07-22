package com.example.kotlinmvvm_rxjava.data.local

import androidx.room.*
import com.example.kotlinmvvm_rxjava.data.entities.ArticleEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao()
interface NewsDao {

    @Query("SELECT * FROM NewsLocalDb")
    fun getAllNews(): Single<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(newsEntity: ArticleEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNews(entityList: List<ArticleEntity>): Completable

    @Query("DELETE FROM NewsLocalDb")
    fun deleteAllEntities(): Completable
}