package com.example.carousellnewsapp.di.module

import android.content.IntentFilter
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {
    @Provides
    fun provideConnectionChangeIntentFilter(): IntentFilter {
        return IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
    }
}