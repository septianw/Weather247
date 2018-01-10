package com.solusi247.weather247.utils

import com.solusi247.weather247.R

fun String.convertToWeatherIconWhite() = when (this) {
    "Thunderstorm" -> R.drawable.ic_thunderstorm_white
    "Cloudy" -> R.drawable.ic_cloudy_white
    "Partly Cloudy" -> R.drawable.ic_partly_cloudy_white
    "Sunny" -> R.drawable.ic_sunny
    "Rainy" -> R.drawable.ic_rainy_white
    else -> 0
}

fun String.convertToWeatherIcon() = when (this) {
    "Thunderstorm" -> R.drawable.ic_thunderstorm
    "Cloudy" -> R.drawable.ic_cloudy
    "Partly Cloudy" -> R.drawable.ic_partly_cloudy
    "Sunny" -> R.drawable.ic_sunny
    "Rainy" -> R.drawable.ic_rainy
    else -> 0
}