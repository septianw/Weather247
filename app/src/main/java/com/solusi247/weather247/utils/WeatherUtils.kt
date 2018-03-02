package com.solusi247.weather247.utils

import android.animation.ValueAnimator
import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.solusi247.weather247.R
import com.solusi247.weather247.Weather247
import java.util.*

object WeatherUtils {

    fun String.convertToWeatherIcon() = when (this) {
        Weather247.context.getString(R.string.cloudy) -> R.drawable.ic_cloudy
        Weather247.context.getString(R.string.sunny) -> R.drawable.ic_sunny
        Weather247.context.getString(R.string.rainy) -> R.drawable.ic_rainy
        else -> 0
    }

    fun String.convertToWhiteWeatherIcon() = when (this) {
        Weather247.context.getString(R.string.cloudy) -> R.drawable.ic_cloudy_white
        Weather247.context.getString(R.string.sunny) -> R.drawable.ic_sunny
        Weather247.context.getString(R.string.rainy) -> R.drawable.ic_rainy_white
        else -> 0
    }

    fun String.convertToLargeWeatherIcon() = when (this) {
        Weather247.context.getString(R.string.cloudy) -> R.drawable.ic_cloudy_large
        Weather247.context.getString(R.string.sunny) -> R.drawable.ic_sunny_large
        Weather247.context.getString(R.string.rainy) -> R.drawable.ic_rainy_large
        else -> 0
    }

    fun String.convertToWhiteLargeWeatherIcon() = when (this) {
        Weather247.context.getString(R.string.cloudy) -> R.drawable.ic_cloudy_white_large
        Weather247.context.getString(R.string.sunny) -> R.drawable.ic_sunny_large
        Weather247.context.getString(R.string.rainy) -> R.drawable.ic_rainy_white_large
        else -> 0
    }

    fun ImageView.startCustomLoading() {
        val animationList = listOf(
                R.drawable.loading_cloudy,
                R.drawable.loading_rainy,
                R.drawable.loading_sunny)
        this.setBackgroundResource(animationList.get(Random().nextInt(animationList.size)))
        val anim = this.background as AnimationDrawable
        this.visibility = View.VISIBLE
        anim.start()
    }

    fun ImageView.endCustomLoading() {
        this.clearAnimation()
        this.visibility = View.GONE
    }

    fun TextView.textAnimationIncrement(number: Any, duration: Long, unit: String? = null) {
        val value = number.toString().toInt()
        val valueAnimator = ValueAnimator.ofInt(0, value).apply {
            setDuration(duration)
            addUpdateListener {
                val text = "${it.getAnimatedValue()}$unit"
                this@textAnimationIncrement.text = text
            }
        }
        valueAnimator.start()
    }
}