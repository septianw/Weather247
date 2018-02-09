package com.solusi247.weather247.utils

import android.content.Context
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Utils
import com.solusi247.weather247.module.model.ResponseModel
import kotlinx.android.synthetic.main.custom_marker_view.view.*

/**
 * Created by aldidwikip on 06/02/2018.
 */

class MyMarkerView(context: Context, layoutResource: Int, val dataDetailWeather: List<ResponseModel.DataDetailWeather>) : MarkerView(context, layoutResource) {

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    override fun refreshContent(e: Entry, highlight: Highlight) {
        var time = ""
        if (e is CandleEntry) {

            val ce = e as CandleEntry?

            tvContent.text = "" + Utils.formatNumber(ce!!.high, 0, true)

            for (i in 0..5) {
                time += dataDetailWeather[i].time
            }

        } else {
            tvContent.text = "" + Utils.formatNumber(e!!.y, 0, true)

            for (i in 0..5) {
                time += dataDetailWeather[i].time
            }
        }
        tvTime.text = time + " "

        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
    }
}
