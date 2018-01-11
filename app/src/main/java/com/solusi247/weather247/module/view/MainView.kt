package com.solusi247.weather247.module.view

import com.solusi247.weather247.module.model.ResponseModel

interface MainView {

    fun onWeatherToday(dataWeather: ResponseModel.DataWeather)

    fun onLastWeather(dataWeathers: List<ResponseModel.DataWeather>)

}