package com.example.kotlinmvvm_rxjava.data.entities

data class ArticleListDto(
    val totalResult: Int,
    val articles: List<ArticleDto>
)