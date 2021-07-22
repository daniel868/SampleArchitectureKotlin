package com.example.kotlinmvvm_rxjava.data.repo

import com.example.kotlinmvvm_rxjava.data.entities.Article
import com.example.kotlinmvvm_rxjava.data.entities.ArticleListDto
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getNewsList(): Single<List<Article>>

}