package com.solusi247.weather247.module.view

import com.solusi247.weather247.module.model.ResponseModel

interface WeatherView {

    fun onListWeather(dataDetailWeathers: List<ResponseModel.DataDetailWeather>)
}