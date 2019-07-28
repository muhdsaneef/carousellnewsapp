package com.example.carousellnewsapp.di.module

import com.example.carousellnewsapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {
    @ContributesAndroidInjector
    abstract fun contributeActivityInjector(): MainActivity
}