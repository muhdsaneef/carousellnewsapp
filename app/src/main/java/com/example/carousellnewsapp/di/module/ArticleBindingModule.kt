package com.example.carousellnewsapp.di.module

import com.example.carousellnewsapp.binding.ArticleBindingAdapter
import com.example.carousellnewsapp.binding.ArticleBindingComponent
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module(includes = [PicassoModule::class])
class ArticleBindingModule {

    @Provides
    fun provideArticleBindingComponent(adapter: ArticleBindingAdapter): ArticleBindingComponent {
        return ArticleBindingComponent(adapter)
    }

    @Provides
    fun provideArticleBindingAdapter(picasso: Picasso) = ArticleBindingAdapter(picasso)
}