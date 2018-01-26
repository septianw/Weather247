package com.solusi247.weather247.module.presenter

import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.module.view.GraphView

class GraphPresenter(val view: GraphView) {

    fun loadGraphTemperature(dataDetailWeathers: List<ResponseModel.DataDetailWeather>) {
        view.onGraphTemperature(dataDetailWeathers.map { it.temperature })
    }

    fun loadGraphPressure(dataDetailWeathers: List<ResponseModel.DataDetailWeather>) {
        view.onGraphPressure(dataDetailWeathers.map { it.pressure })
    }

    fun loadGraphHumidity(dataDetailWeathers: List<ResponseModel.DataDetailWeather>) {
        view.onGraphHumidity(dataDetailWeathers.map { it.humidity })
    }

}