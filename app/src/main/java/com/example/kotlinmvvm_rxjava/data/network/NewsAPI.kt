package com.example.kotlinmvvm_rxjava.data.network

import com.example.kotlinmvvm_rxjava.data.entities.ArticleListDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    companion object {
        const val BASE_URL = "https://newsapi.org/"
        const val CLIENT_ID = "78qIrlCLjscImartPKDSFG8rwhdbYhbhmTiMFMIwrqI"
    }

//    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
//    @GET("search/photos")
//    fun getPhotoList(
//        @Query("query") query: String,
//        @Query("page") page: Int,
//        @Query("per_page") perPage: Int,
//    ): Single<UnsplashResponse>

    @GET("/v2/top-headlines")
    fun getPhotoList(
        @Query("apiKey") apiKey: String,
        @Query("language") language: String
    ): Single<ArticleListDto>
}