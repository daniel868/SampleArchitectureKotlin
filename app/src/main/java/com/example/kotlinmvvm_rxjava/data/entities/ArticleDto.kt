package com.example.kotlinmvvm_rxjava.data.entities

data class ArticleDto(
    val source: SourceDto,
    val urlToImage: String,
    val url: String,
    val content: String,
    val description: String,
    val title: String,
    val author: String
) {
}