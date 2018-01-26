package com.solusi247.weather247.module.view

interface GraphView {

    fun onGraphTemperature(temperatures: List<String>)
    fun onGraphHumidity(humidity: List<String>)
    fun onGraphPressure(pressure: List<String>)
}