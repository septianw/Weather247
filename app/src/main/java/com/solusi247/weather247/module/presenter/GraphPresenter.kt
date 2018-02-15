package com.solusi247.weather247.module.presenter

import android.content.Context
import android.support.v4.content.ContextCompat
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.solusi247.weather247.R
import com.solusi247.weather247.Weather247
import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.module.view.GraphView

class GraphPresenter(val view: GraphView) {

    val context: Context

    init {
        context = Weather247.context
    }

    fun loadGraphTemperature(dataDetailWeathers: List<ResponseModel.DataDetailWeather>) {

        val time = dataDetailWeathers.map { it.time }
        val temperatures = dataDetailWeathers.map { it.temperature }

        val entries = arrayListOf<Entry>()
        for (i in temperatures.indices) {
            entries.add(Entry(i.toFloat(), temperatures[i].toFloat(), time[i]))
        }

        val lineDataSet = LineDataSet(entries, context.getString(R.string.temperature)).apply {
            color = ContextCompat.getColor(context, R.color.colorGraph)
            valueTextSize = 12f
            setDrawFilled(true)
            fillAlpha = 50
            lineWidth = 3f
            fillColor = ContextCompat.getColor(context, R.color.colorGraph)
            mode = LineDataSet.Mode.HORIZONTAL_BEZIER
            setCircleColor(ContextCompat.getColor(context, R.color.colorSecondary))
            circleRadius = 5f
        }

        val dataSets = arrayListOf<ILineDataSet>(lineDataSet)
        val lineData = LineData(dataSets)

        view.onGraphTemperature(lineData)
    }

    fun loadGraphPressure(dataDetailWeathers: List<ResponseModel.DataDetailWeather>) {

        val time = dataDetailWeathers.map { it.time }
        val pressure = dataDetailWeathers.map { it.pressure }

        val entries = arrayListOf<Entry>()
        for (i in pressure.indices) {
            entries.add(Entry(i.toFloat(), pressure[i].toFloat(), time[i]))
        }

        val lineDataSet = LineDataSet(entries, context.getString(R.string.pressure)).apply {
            color = ContextCompat.getColor(context, R.color.colorGraph)
            valueTextSize = 12f
            setDrawFilled(true)
            fillAlpha = 50
            lineWidth = 3f
            fillColor = ContextCompat.getColor(context, R.color.colorGraph)
            mode = LineDataSet.Mode.HORIZONTAL_BEZIER
            setCircleColor(ContextCompat.getColor(context, R.color.colorSecondary))
            circleRadius = 5f
        }

        val dataSets = arrayListOf<ILineDataSet>(lineDataSet)
        val lineData = LineData(dataSets)

        view.onGraphPressure(lineData)
    }

    fun loadGraphHumidity(dataDetailWeathers: List<ResponseModel.DataDetailWeather>) {

        val time = dataDetailWeathers.map { it.time }
        val humidity = dataDetailWeathers.map { it.humidity }

        val entries = arrayListOf<Entry>()
        for (i in humidity.indices) {
            entries.add(Entry(i.toFloat(), humidity[i].toFloat(), time[i]))
        }

        val lineDataSet = LineDataSet(entries, context.getString(R.string.humidity)).apply {
            color = ContextCompat.getColor(context, R.color.colorGraph)
            valueTextSize = 12f
            setDrawFilled(true)
            fillAlpha = 50
            lineWidth = 3f
            fillColor = ContextCompat.getColor(context, R.color.colorGraph)
            mode = LineDataSet.Mode.HORIZONTAL_BEZIER
            setCircleColor(ContextCompat.getColor(context, R.color.colorSecondary))
            circleRadius = 5f
        }

        val dataSets = arrayListOf<ILineDataSet>(lineDataSet)
        val lineData = LineData(dataSets)

        view.onGraphHumidity(lineData)

    }

}