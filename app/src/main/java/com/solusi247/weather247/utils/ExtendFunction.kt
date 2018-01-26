package com.solusi247.weather247.utils

import android.animation.ValueAnimator
import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.solusi247.weather247.R
import com.solusi247.weather247.Weather247
import java.text.SimpleDateFormat
import java.util.*

fun String.convertToWeatherIcon() = when (this) {
    Weather247.context.getString(R.string.thunderstorm) -> R.drawable.ic_thunderstorm
    Weather247.context.getString(R.string.cloudy) -> R.drawable.ic_cloudy
    Weather247.context.getString(R.string.partly_cloudy) -> R.drawable.ic_partly_cloudy
    Weather247.context.getString(R.string.sunny) -> R.drawable.ic_sunny
    Weather247.context.getString(R.string.rainy) -> R.drawable.ic_rainy
    else -> 0
}

fun String.convertToLargeWeatherIcon() = when (this) {
    Weather247.context.getString(R.string.thunderstorm) -> R.drawable.ic_thunderstorm_large
    Weather247.context.getString(R.string.cloudy) -> R.drawable.ic_cloudy_large
    Weather247.context.getString(R.string.partly_cloudy) -> R.drawable.ic_partly_cloudy_large
    Weather247.context.getString(R.string.sunny) -> R.drawable.ic_sunny_large
    Weather247.context.getString(R.string.rainy) -> R.drawable.ic_rainy_large
    else -> 0
}

fun String.changeFormatDate(): String {
    val date = SimpleDateFormat("yyyy-mm-dd").parse(this)
    val dateString = SimpleDateFormat("dd MMM yyyy").format(date)
    return dateString
}

fun ImageView.startCustomLoading() {
    val animationList = listOf(
            R.drawable.loading_cloudy,
            R.drawable.loading_partly_cloudy,
            R.drawable.loading_rainy,
            R.drawable.loading_sunny,
            R.drawable.loading_thunderstorm)
    this.setBackgroundResource(animationList.get(Random().nextInt(animationList.size)))
    val anim = this.background as AnimationDrawable
    this.visibility = View.VISIBLE
    anim.start()
}

fun ImageView.endCustomLoading() {
    this.clearAnimation()
    this.visibility = View.INVISIBLE
}

fun TextView.textAnimationIncrement(number: Any, unit: String? = null) {
    val value = number.toString().toInt()
    val valueAnimator = ValueAnimator.ofInt(0, value)
    valueAnimator.setDuration(1000)
    valueAnimator.addUpdateListener { animation ->
        val text = "${animation.getAnimatedValue()}$unit"
        this.text = text
    }
    valueAnimator.start()
}


