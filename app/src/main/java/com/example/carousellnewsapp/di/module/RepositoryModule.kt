package com.example.carousellnewsapp.di.module

import com.example.carousellnewsapp.api.NewsApi
import com.example.carousellnewsapp.repository.ArticlesRepository
import dagger.Module
import dagger.Provides

@Module(includes = [RetrofitModule::class])
class RepositoryModule {
    @Provides
    fun provideArticlesRepository(newsApi: NewsApi): ArticlesRepository {
        return ArticlesRepository(newsApi)
    }
}