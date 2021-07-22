package com.example.kotlinmvvm_rxjava.data

import com.example.kotlinmvvm_rxjava.data.entities.Article
import com.example.kotlinmvvm_rxjava.data.entities.ArticleListDto
import com.example.kotlinmvvm_rxjava.data.mappers.ArticleDtoToArticleMapper
import com.example.kotlinmvvm_rxjava.data.network.NewsAPI
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NewsRemoteSource @Inject constructor(private val newsAPI: NewsAPI) {
    companion object {
        const val API_KEY = "dec01c28382047e8a74eaa1817dacaaa"
        const val EN_LANGUAGE_FILTER = "en";
    }

    fun getPhotoArticle(): Single<List<Article>> =
        newsAPI.getPhotoList(API_KEY, EN_LANGUAGE_FILTER)
            .subscribeOn(Schedulers.io())
            .map(ArticleDtoToArticleMapper())

}