package com.example.kotlinmvvm_rxjava.data.mappers

import com.example.kotlinmvvm_rxjava.data.entities.Article
import com.example.kotlinmvvm_rxjava.data.entities.ArticleListDto
import io.reactivex.rxjava3.functions.Function

class ArticleDtoToArticleMapper : Function<ArticleListDto, List<Article>> {
    override fun apply(articleListDto: ArticleListDto): List<Article> {
        val articles = ArrayList<Article>()
        for (currentArticle in articleListDto.articles) {
            val article = Article(
                currentArticle.urlToImage ?: "",
                currentArticle.title ?: "",
                currentArticle.content ?: "",
                currentArticle.description ?: ""
            )
            articles.add(article)
        }
        return articles
    }
}