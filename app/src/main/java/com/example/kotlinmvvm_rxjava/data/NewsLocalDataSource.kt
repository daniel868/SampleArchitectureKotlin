package com.example.kotlinmvvm_rxjava.data

import android.util.Log
import com.example.kotlinmvvm_rxjava.data.entities.Article
import com.example.kotlinmvvm_rxjava.data.entities.ArticleEntity
import com.example.kotlinmvvm_rxjava.data.local.NewsDao
import com.example.kotlinmvvm_rxjava.data.mappers.ArticleToArticleEntityMapper
import com.example.kotlinmvvm_rxjava.data.mappers.KotlinMappers.ArticleEntityToArticleMapper
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(private val newsDao: NewsDao) {
    private val TAG = "NewsLocalDataSource"

    fun getAllNews(): Single<List<Article>> {
        return newsDao.getAllNews()
            .map(ArticleEntityToArticleMapper())
    }

    fun insertEntity(entity: ArticleEntity): Completable {
        return newsDao.insertNews(entity)
    }

    fun saveAllEntity(articleList: List<Article>) {
        newsDao.deleteAllEntities()
            .andThen(
                Single.just(articleList)
                    .map(ArticleToArticleEntityMapper())
                    .flatMapCompletable {
                        newsDao.insertAllNews(it)
                    })
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d(TAG, "Entities saved to the DB")
            }, { throwable ->
                Log.e(TAG, "saveAllEntity: ", throwable)
            })
    }

    fun deleteAllNews() {
        newsDao.deleteAllEntities()
    }
}