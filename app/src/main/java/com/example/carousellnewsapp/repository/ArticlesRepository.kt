package com.example.carousellnewsapp.repository

import com.example.carousellnewsapp.api.NewsApi
import com.example.carousellnewsapp.models.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesRepository(private val newsApi: NewsApi) {

    var errorCallback: (() -> Unit)? = null
    var successCallback: ((response: Response<List<Article>>) -> Unit)? = null

    fun fetchNewsArticle() {
        val apiCall = newsApi.fetchNewsArticles()
        apiCall.enqueue(object : Callback<List<Article>> {
            override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                errorCallback?.invoke()
            }

            override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
                successCallback?.invoke(response)
            }
        })
    }

}