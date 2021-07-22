package com.example.kotlinmvvm_rxjava.data.repo

import android.util.Log
import com.example.kotlinmvvm_rxjava.data.NewsLocalDataSource
import com.example.kotlinmvvm_rxjava.data.NewsRemoteSource
import com.example.kotlinmvvm_rxjava.data.entities.Article
import com.example.kotlinmvvm_rxjava.data.entities.ArticleListDto
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RepositoryImplementation constructor(
    private val newsRemoteSource: NewsRemoteSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : Repository {
    private val TAG = "RepositoryImplementatio"

    override fun getNewsList(): Single<List<Article>> =
        newsRemoteSource.getPhotoArticle()
            .doOnSuccess { articleList ->
                newsLocalDataSource.saveAllEntity(articleList)
            }.doOnError {
                newsLocalDataSource.getAllNews()
            }

}