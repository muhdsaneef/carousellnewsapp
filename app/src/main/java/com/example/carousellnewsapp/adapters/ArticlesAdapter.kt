package com.example.carousellnewsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.carousellnewsapp.R
import com.example.carousellnewsapp.binding.ArticleBindingComponent
import com.example.carousellnewsapp.databinding.ItemArticleBinding
import com.example.carousellnewsapp.models.Article
import javax.inject.Inject

class ArticlesAdapter @Inject constructor() : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {
    lateinit var articleBindingComponent: ArticleBindingComponent
    private var listOfArticles: MutableList<Article> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemArticleBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_article,
            parent, false, articleBindingComponent
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfArticles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindArticleToView(listOfArticles[position])
    }

    fun addArticles(articlesList: List<Article>) {
        listOfArticles.clear()
        listOfArticles.addAll(articlesList)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindArticleToView(article: Article) {
            binding.obj = article
            binding.executePendingBindings()
        }
    }
}