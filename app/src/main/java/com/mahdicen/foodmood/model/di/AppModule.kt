package com.mahdicen.foodmood.model.di

import android.app.Application
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mahdicen.foodmood.ext.BaseUrl
import com.mahdicen.foodmood.ext.NetworkChecker
import com.mahdicen.foodmood.model.net.ApiService
import com.mahdicen.foodmood.model.repo.category.CategoryRepository
import com.mahdicen.foodmood.model.repo.category.CategoryRepositoryImpl
import com.mahdicen.foodmood.model.repo.main.MainRepository
import com.mahdicen.foodmood.model.repo.main.MainRepositoryImpl
import com.mahdicen.foodmood.model.repo.search.SearchRepository
import com.mahdicen.foodmood.model.repo.search.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMainRepository(apiService: ApiService): MainRepository {
        return MainRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(apiService: ApiService): SearchRepository {
        return SearchRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(apiService: ApiService): CategoryRepository {
        return CategoryRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideNetChecker(application: Application): NetworkChecker {
        return NetworkChecker(application.applicationContext)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
         return retrofit.create(ApiService::class.java)
    }
}