package com.example.carousellnewsapp.viewmodels

import android.view.MenuItem
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carousellnewsapp.R
import com.example.carousellnewsapp.di.component.DaggerViewModelComponent
import com.example.carousellnewsapp.models.Article
import com.example.carousellnewsapp.repository.ArticlesRepository
import retrofit2.Response
import javax.inject.Inject

class ArticlesViewModel : ViewModel() {

    @Inject
    lateinit var articlesRepository: ArticlesRepository

    private val viewModelComponent = DaggerViewModelComponent.builder().build()
    private val articleList = MutableLiveData<List<Article>>()
    private val errorMessage = MutableLiveData<Int>()

    var loadingInProgress = ObservableBoolean()
    var articlesFound = ObservableBoolean()
    var isNetworkAvailable = true

    init {
        viewModelComponent.inject(this)

        articlesRepository.errorCallback = {
            loadingInProgress.set(false)
            handleErrorResponse()
        }

        articlesRepository.successCallback = {
            loadingInProgress.set(false)
            handleSuccessResponse(it)
        }
    }

    private fun handleSuccessResponse(response: Response<List<Article>>) {
        if (response.isSuccessful) {
            val articleList = response.body()
            if (articleList != null) {
                articlesFound.set(articleList.isNotEmpty())
                val sortedArticlesList = articleList.sortedWith(compareBy { -it.timeCreated })
                this.articleList.value = sortedArticlesList
            } else {
                handleErrorResponse()
            }
        } else {
            handleErrorResponse()
        }
    }

    fun getErrorMessage(): LiveData<Int> = errorMessage

    private fun handleErrorResponse() {
        if (isNetworkAvailable) {
            errorMessage.value = R.string.something_went_wrong
        } else {
            errorMessage.value = R.string.no_network_available
        }
    }

    fun getArticlesList(): LiveData<List<Article>> = articleList

    fun sortByCustomFilter(optionSelected: MenuItem?) {
        optionSelected?.let {
            if (it.itemId == R.id.option_popular) {
                filterArticles(true)
            } else if (it.itemId == R.id.option_recent) {
                filterArticles(false)
            }
        }
    }

    private fun filterArticles(shouldFilterByPopularity: Boolean) {
        var currentArticles = articleList.value
        currentArticles?.let {
            if (shouldFilterByPopularity) {
                articleList.value = currentArticles.sortedWith(compareBy({ -it.rank }, { -it.timeCreated }))
            } else {
                articleList.value = currentArticles.sortedWith(compareBy { -it.timeCreated })
            }
        }
    }

    fun fetchNewsArticles() {
        if (isNetworkAvailable) {
            loadingInProgress.set(true)
            articlesRepository.fetchNewsArticle()
        } else {
            handleErrorResponse()
        }
    }

}