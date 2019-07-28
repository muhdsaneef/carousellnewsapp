package com.example.carousellnewsapp.di.component

import com.example.carousellnewsapp.di.module.RepositoryModule
import com.example.carousellnewsapp.viewmodels.ArticlesViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface ViewModelComponent {
    fun inject(articlesViewModel: ArticlesViewModel)
}