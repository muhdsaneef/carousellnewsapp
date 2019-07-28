package com.example.carousellnewsapp.binding

import androidx.databinding.DataBindingComponent

class ArticleBindingComponent(val adapter: ArticleBindingAdapter) : DataBindingComponent {
    override fun getArticleBindingAdapter(): ArticleBindingAdapter {
        return adapter
    }
}