package com.example.carousellnewsapp.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.carousellnewsapp.util.Constants
import com.example.carousellnewsapp.util.DateTimeUtils
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ArticleBindingAdapter @Inject constructor(val picasso: Picasso) {
    @BindingAdapter("bannerImage")
    fun loadImage(imageView: ImageView, url: String) {
        picasso.load(url).into(imageView)
    }

    @BindingAdapter("relativeTime")
    fun setRelativeTime(textView: TextView, timeStamp: Long) {
        val timeStampInMillis = timeStamp * Constants.TIMESTAMP_MULTIPLY_FACTOR
        val relativeTime = DateTimeUtils.getRelativeTime(timeStampInMillis)
        textView.text = relativeTime
    }
}