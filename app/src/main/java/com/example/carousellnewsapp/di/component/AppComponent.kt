package com.example.carousellnewsapp.di.component

import android.app.Application
import com.example.carousellnewsapp.app.CarousellApplication
import com.example.carousellnewsapp.di.module.ActivityModule
import com.example.carousellnewsapp.di.module.AppModule
import com.example.carousellnewsapp.di.module.ArticleBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityModule::class, ArticleBindingModule::class])
interface AppComponent : AndroidInjector<CarousellApplication> {}