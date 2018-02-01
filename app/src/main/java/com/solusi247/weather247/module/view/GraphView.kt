package com.solusi247.weather247.module.view

import com.github.mikephil.charting.data.LineData

interface GraphView {

    fun onGraphTemperature(lineDataTemperature: LineData)
    fun onGraphPressure(lineDataPressure: LineData)
    fun onGraphHumidity(lineDataHumidity: LineData)
}