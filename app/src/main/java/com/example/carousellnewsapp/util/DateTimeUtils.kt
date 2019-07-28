package com.example.carousellnewsapp.util

import java.util.concurrent.TimeUnit

object DateTimeUtils {
    fun getRelativeTime(timeCreated: Long): String {
        var relativeTime = ""
        val timeDifference = System.currentTimeMillis() - timeCreated
        val second = TimeUnit.MILLISECONDS.toSeconds(timeDifference)
        val minute = TimeUnit.MILLISECONDS.toMinutes(timeDifference)
        val hour = TimeUnit.MILLISECONDS.toHours(timeDifference)
        var day = TimeUnit.MILLISECONDS.toDays(timeDifference)
        when {
            second < Constants.ONE_MINUTE_IN_SECONDS -> relativeTime = second.toString() +
                                                        Constants.RELATIVE_TIME_SECONDS + Constants.RELATIVE_TIME_SUFFIX
            minute < Constants.ONE_HOUR_IN_MINUTES -> relativeTime = minute.toString() +
                                                        Constants.RELATIVE_TIME_MINUTES + Constants.RELATIVE_TIME_SUFFIX
            hour < Constants.ONE_DAY_IN_HOURS -> relativeTime = hour.toString() +
                                                        Constants.RELATIVE_TIME_HOURS + Constants.RELATIVE_TIME_SUFFIX
            day >= Constants.ONE_WEEK_IN_DAYS -> relativeTime = when {
                day > Constants.ONE_YEAR_IN_DAYS -> { (day / Constants.ONE_YEAR_IN_DAYS).toString() +
                            Constants.RELATIVE_TIME_YEARS + Constants.RELATIVE_TIME_SUFFIX }
                day > Constants.ONE_MONTH_IN_DAYS -> {
                    (day / Constants.ONE_MONTH_IN_DAYS).toString() +
                            Constants.RELATIVE_TIME_MONTHS + Constants.RELATIVE_TIME_SUFFIX}
                else -> { (day / Constants.ONE_WEEK_IN_DAYS).toString() + Constants.RELATIVE_TIME_WEEKS +
                            Constants.RELATIVE_TIME_SUFFIX}
            }
            else -> relativeTime = day.toString() + Constants.RELATIVE_TIME_DAYS + Constants.RELATIVE_TIME_SUFFIX
        }
        if (hour == 1L || minute == 1L || checkForUnitDayValue(day)) {
            relativeTime = relativeTime.replace("s","")
        }
        if (second == 1L) {
            relativeTime = relativeTime.replace(Constants.RELATIVE_TIME_SECONDS, Constants.RELATIVE_TIME_SECOND)
        }
        return relativeTime
    }

    private fun checkForUnitDayValue(day: Long): Boolean{
        return (day/Constants.ONE_YEAR_IN_DAYS).toString() == Constants.UNIT_DAY_TEXT ||
                (day/Constants.ONE_MONTH_IN_DAYS).toString() == Constants.UNIT_DAY_TEXT ||
                (day/Constants.ONE_WEEK_IN_DAYS).toString() == Constants.UNIT_DAY_TEXT
    }

}