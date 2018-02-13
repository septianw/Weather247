package com.solusi247.weather247.listener

import com.solusi247.weather247.module.model.ResponseModel

interface ListWeatherListener {

    fun onListClicked(dataDetailWeather: ResponseModel.DataDetailWeather)
}