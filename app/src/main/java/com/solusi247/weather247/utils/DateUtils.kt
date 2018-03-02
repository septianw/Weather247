package com.solusi247.weather247.utils

import android.text.format.DateUtils
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun String.changeFormatDate(): String {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(this)
        val dateString = SimpleDateFormat("dd MMM yyyy", Locale.US).format(date)
        return dateString
    }

    fun String.changeDateToLong(): Long {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val date = sdf.parse(this)
        val dateLong = date.time
        return dateLong
    }

    fun getDateNow(): Date {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return sdf.parse(sdf.format(Date()))
    }

    fun timeDiffFromNow(timeFirst: String): Long {
        val sdf = SimpleDateFormat("HH:mm", Locale.US)
        val date1 = sdf.parse(timeFirst)
        val date2 = sdf.parse(sdf.format(Date()))
        return date2.time - date1.time
    }

    fun isPrediction(date: String, time: String): Boolean {
        if (DateUtils.isToday(date.changeDateToLong())) {
            Log.i("timediff", timeDiffFromNow(time).toString())
            return timeDiffFromNow(time) < 0
        } else {
            return getDateNow().time - date.changeDateToLong() < 0
        }
    }
}