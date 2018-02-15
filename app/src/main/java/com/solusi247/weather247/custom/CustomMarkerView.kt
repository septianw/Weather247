package com.solusi247.weather247.custom

import android.content.Context
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.solusi247.weather247.R
import com.solusi247.weather247.Weather247
import kotlinx.android.synthetic.main.custom_marker_view.view.*

class CustomMarkerView(context: Context, layoutResource: Int) : MarkerView(context, layoutResource) {

    constructor(context: Context) : this(context, 0)

    override fun refreshContent(e: Entry, highlight: Highlight) {

        tvTime.text = String.format(Weather247.context.getString(R.string.time_text), e.data.toString())
        tvData.text = String.format(Weather247.context.getString(R.string.data_text), e.y.toString())

        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF(0f, (-height / 2).toFloat())
    }
}
