package com.example.carousellnewsapp.util

class Constants private constructor() {
    companion object {
        const val BASE_API_URL = "https://storage.googleapis.com/"
        const val NEWS_URL = "carousell-interview-assets/android/carousell_news.json"
        //Relative time constants
        const val TIMESTAMP_MULTIPLY_FACTOR = 1000
        const val RELATIVE_TIME_SECONDS = " seconds"
        const val RELATIVE_TIME_SECOND = " second"
        const val RELATIVE_TIME_MINUTES = " minutes"
        const val RELATIVE_TIME_HOURS = " hours"
        const val RELATIVE_TIME_DAYS = " days"
        const val RELATIVE_TIME_WEEKS = " weeks"
        const val RELATIVE_TIME_MONTHS = " months"
        const val RELATIVE_TIME_YEARS = " years"
        const val RELATIVE_TIME_SUFFIX = " ago"
        const val ONE_MINUTE_IN_SECONDS = 60
        const val ONE_HOUR_IN_MINUTES = 60
        const val ONE_DAY_IN_HOURS = 24
        const val ONE_YEAR_IN_DAYS = 360
        const val ONE_WEEK_IN_DAYS = 7
        const val ONE_MONTH_IN_DAYS = 30
        const val UNIT_DAY_TEXT = "1"
    }
}