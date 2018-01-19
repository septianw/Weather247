package com.solusi247.weather247.module.presenter

import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.module.view.WeatherView

class WeatherPresenter(val view: WeatherView) {

    fun initWeatherDetails(dataDetailWeathers: List<ResponseModel.DataDetailWeather>) {
        view.onListWeather(dataDetailWeathers)
    }
}