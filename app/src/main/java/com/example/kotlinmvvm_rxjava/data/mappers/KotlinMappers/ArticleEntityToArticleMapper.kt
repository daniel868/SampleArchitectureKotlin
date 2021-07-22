package com.example.kotlinmvvm_rxjava.data.mappers.KotlinMappers

import com.example.kotlinmvvm_rxjava.data.entities.Article
import com.example.kotlinmvvm_rxjava.data.entities.ArticleEntity
import io.reactivex.rxjava3.functions.Function

class ArticleEntityToArticleMapper : Function<List<ArticleEntity>, List<Article>> {
    override fun apply(entityList: List<ArticleEntity>?): List<Article> {
        val articleList = ArrayList<Article>()
        for (currentEntity in entityList!!) {
            val article = Article(
                currentEntity.imageUrl,
                currentEntity.title,
                currentEntity.content,
                currentEntity.description
            )
            articleList.add(article)
        }
        return articleList
    }
}