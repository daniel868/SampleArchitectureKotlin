package com.example.kotlinmvvm_rxjava.di

import android.app.Application
import com.example.kotlinmvvm_rxjava.data.NewsRemoteSource
import com.example.kotlinmvvm_rxjava.data.network.HttpSuccessInterceptor
import com.example.kotlinmvvm_rxjava.data.network.NewsAPI
import com.example.kotlinmvvm_rxjava.data.repo.Repository
import com.example.kotlinmvvm_rxjava.data.repo.RepositoryImplementation
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RepoModule(val application: Application) {


//    fun provideRepository(): Repository =
//        RepositoryImplementation(provideNewsRemoteSource())

    private fun provideNewsRemoteSource(): NewsRemoteSource =
        NewsRemoteSource(provideImageAPI())

    private fun provideImageAPI(): NewsAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(NewsAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(provideHttpClient(provideHttpSuccessInterceptor()))
            .build()
        return retrofit.create(NewsAPI::class.java)
    }

    private fun provideHttpSuccessInterceptor(): HttpSuccessInterceptor {
        return HttpSuccessInterceptor()
    }

    private fun provideHttpClient(httpSuccessInterceptor: HttpSuccessInterceptor): OkHttpClient {
        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder = OkHttpClient.Builder()
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(false)
            .addInterceptor(httpSuccessInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        return builder
    }
}