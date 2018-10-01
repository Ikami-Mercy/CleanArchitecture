package com.gelostech.cleanarchitecture.di.modules

import android.arch.lifecycle.ViewModelProvider
import com.gelostech.cleanarchitecture.data.daos.PostsDao
import com.gelostech.cleanarchitecture.data.repositories.PostsRepository
import com.gelostech.cleanarchitecture.di.ViewModelFactory
import com.gelostech.cleanarchitecture.utils.ApiService
import com.gelostech.cleanarchitecture.utils.Endpoints
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val builder = GsonBuilder()
        return builder.create()
    }

    @Provides
    @Singleton
    fun provideOkhttp(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .baseUrl(Endpoints.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Provides
    @Singleton
    fun getApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePostsRepository(apiService: ApiService, postsDao: PostsDao): PostsRepository {
        return PostsRepository(apiService, postsDao)
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(postsRepository: PostsRepository): ViewModelProvider.Factory {
        return ViewModelFactory(postsRepository)
    }

}