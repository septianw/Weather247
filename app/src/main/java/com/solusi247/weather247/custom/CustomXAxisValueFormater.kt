package com.solusi247.weather247.custom

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter

class CustomXAxisValueFormater(val times: List<String>) : IAxisValueFormatter {

    override fun getFormattedValue(value: Float, axis: AxisBase): String {
        return times[value.toInt()]
    }

}