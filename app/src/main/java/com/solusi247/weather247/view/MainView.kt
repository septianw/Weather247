package com.solusi247.weather247.view

import com.solusi247.weather247.model.ResponseModel

interface MainView {

    fun onWeatherToday(dataWeather: ResponseModel.DataWeather)

    fun onLastWeather(dataWeathers: List<ResponseModel.DataWeather>)

}