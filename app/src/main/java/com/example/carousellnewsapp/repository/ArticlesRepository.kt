package com.example.carousellnewsapp.repository

import com.example.carousellnewsapp.api.NewsApi
import com.example.carousellnewsapp.models.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class ArticlesRepository(private val newsApi: NewsApi) {

    var errorCallback: (() -> Unit)? = null
    var successCallback: ((response: Response<List<Article>>) -> Unit)? = null

    fun fetchNewsArticle() {
        val apiCall = newsApi.fetchNewsArticles()
        val apiCallSubscription = apiCall.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response -> successCallback?.invoke(response)},
                 {errorCallback?.invoke()})
    }
}