package com.example.kotlinmvvm_rxjava.di

import com.example.kotlinmvvm_rxjava.data.NewsLocalDataSource
import com.example.kotlinmvvm_rxjava.data.NewsRemoteSource
import com.example.kotlinmvvm_rxjava.data.network.HttpSuccessInterceptor
import com.example.kotlinmvvm_rxjava.data.network.NewsAPI
import com.example.kotlinmvvm_rxjava.data.repo.Repository
import com.example.kotlinmvvm_rxjava.data.repo.RepositoryImplementation
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(newsRemoteSource: NewsRemoteSource, newsLocalSource:NewsLocalDataSource): Repository {
        return RepositoryImplementation(newsRemoteSource,newsLocalSource)
    }

    @Singleton
    @Provides
    fun provideImageAPI(builder: OkHttpClient): NewsAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(NewsAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(builder)
            .build()
        return retrofit.create(NewsAPI::class.java)
    }
    
    @Singleton
    @Provides
    fun provideHttpClient(httpSuccessor: HttpSuccessInterceptor): OkHttpClient {
        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder = OkHttpClient.Builder()
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(false)
            .addInterceptor(httpSuccessor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        return builder

    }
}
