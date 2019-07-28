package com.example.carousellnewsapp.di.module

import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
class PicassoModule {
    @Provides
    fun providePicasso() = Picasso.get()
}