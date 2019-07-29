package com.example.carousellnewsapp.ui

import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.carousellnewsapp.R
import com.example.carousellnewsapp.adapters.ArticlesAdapter
import com.example.carousellnewsapp.binding.ArticleBindingComponent
import com.example.carousellnewsapp.databinding.ActivityMainBinding
import com.example.carousellnewsapp.receivers.ConnectivityChangeReceiver
import com.example.carousellnewsapp.util.VerticalSpaceItemDecoration
import com.example.carousellnewsapp.viewmodels.ArticlesViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    //region Dependency injections
    @Inject
    lateinit var articleBindingComponent: ArticleBindingComponent

    @Inject
    lateinit var articlesAdapter: ArticlesAdapter

    @Inject
    lateinit var verticalSpaceItemDecoration: VerticalSpaceItemDecoration

    @Inject
    lateinit var intentFilter: IntentFilter

    @Inject
    lateinit var connectivityChangeReceiver: ConnectivityChangeReceiver

    //endregion

    private lateinit var viewModel: ArticlesViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        AndroidInjection.inject(this)

        initToolbar()
        initViewModel()
        setObserverForConnectivityChanges()
        setObserverForErrorMessages()
        setObserverForArticleListChanges()
        initArticlesAdapter()
    }

    private fun initToolbar() {
        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
    }

    private fun setObserverForArticleListChanges() {
        viewModel.getArticlesList().observe(this, Observer {
            articlesAdapter.addArticles(it)
            binding.content.rvArticles.smoothScrollToPosition(0)
        })
    }

    private fun setObserverForErrorMessages() {
        viewModel.getErrorMessage().observe(this, Observer {
            showToastMessage(it)
        })
    }

    private fun showToastMessage(resourceId: Int?) {
        resourceId?.let {
            Toast.makeText(this, getString(resourceId), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(connectivityChangeReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(connectivityChangeReceiver)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.filter_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        viewModel.sortByCustomFilter(item)
        return true
    }

    private fun setObserverForConnectivityChanges() {
        connectivityChangeReceiver.connectionChangeListener = {
            viewModel.setNetworkStatus(it)
        }
    }

    private fun initArticlesAdapter() {
        articlesAdapter.articleBindingComponent = articleBindingComponent
        val verticalSpaceHeight = resources.getDimensionPixelSize(R.dimen.vertical_space_height)
        verticalSpaceItemDecoration.verticalSpaceHeight = verticalSpaceHeight
        binding.content.rvArticles.addItemDecoration(verticalSpaceItemDecoration)
        binding.content.rvArticles.adapter = articlesAdapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ArticlesViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.fetchNewsArticles()

    }
}
