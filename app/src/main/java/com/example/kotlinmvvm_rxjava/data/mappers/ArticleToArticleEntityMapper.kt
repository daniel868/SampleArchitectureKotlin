package com.example.kotlinmvvm_rxjava.data.mappers

import com.example.kotlinmvvm_rxjava.data.entities.Article
import com.example.kotlinmvvm_rxjava.data.entities.ArticleEntity
import io.reactivex.rxjava3.functions.Function
import java.util.*
import kotlin.collections.ArrayList

class ArticleToArticleEntityMapper : Function<List<Article>, List<ArticleEntity>> {
    override fun apply(articleList: List<Article>?): List<ArticleEntity> {
        val entitiesList = ArrayList<ArticleEntity>()
        if (articleList != null) {
            for (currentArticle in articleList) {
                val articleEntity = ArticleEntity(
                    Random().nextInt(),
                    currentArticle.title ?: "",
                    currentArticle.content ?: "",
                    currentArticle.description ?: "",
                    currentArticle.imageUrl ?: ""
                )
                entitiesList.add(articleEntity)
            }
        }
        return entitiesList
    }
}