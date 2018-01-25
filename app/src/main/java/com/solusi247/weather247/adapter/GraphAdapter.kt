package com.solusi247.weather247.adapter

import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.solusi247.weather247.R
import com.solusi247.weather247.module.model.ResponseModel
import kotlinx.android.synthetic.main.list_graph_item.view.*


/**
 * Created by aldidwikip on 25/01/2018.
 */
class GraphAdapter(val dataDetailWeathers: List<ResponseModel.DataDetailWeather>)
    : RecyclerView.Adapter<GraphAdapter.GraphViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GraphViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_graph_item, parent, false)
        return GraphViewHolder(view)
    }

    override fun onBindViewHolder(holder: GraphViewHolder, position: Int) {
        with(holder) {
            graph(dataDetailWeathers)
        }
    }

    override fun getItemCount() = 1

    class GraphViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvGraphs: CardView
        val lineChart: LineChart

        init {
            cvGraphs = itemView.cvGraphs
            lineChart = itemView.lineChart
        }

        fun graph(dataDetailWeathers: List<ResponseModel.DataDetailWeather>) {
            lineChart.isDragEnabled = true
            lineChart.isScaleYEnabled = false
            //lineChart.animateY(3000, Easing.EasingOption.EaseInOutBounce)
            //lineChart.animateX(6000, Easing.EasingOption.EaseInOutElastic)
            lineChart.animateXY(2000, 2000)
            lineChart.setScaleMinima(3f, 0f)
            val desc = Description()
            desc.text = ""
            lineChart.description = desc
            lineChart.setNoDataText("No Data")
            lineChart.axisLeft.axisMinimum = 10f
            lineChart.axisLeft.axisMaximum = 40f
            lineChart.axisRight.isEnabled = false
            lineChart.xAxis.granularity = 1f

            val xLabels: ArrayList<String> = ArrayList()
            for (t in dataDetailWeathers.size - 1..0) {
                xLabels.add(dataDetailWeathers[t].time)
            }

            val yTValues: ArrayList<Entry> = ArrayList()

            var j = 0
            for (i in dataDetailWeathers.size - 1 downTo 0) {
                yTValues.add(Entry(j.toFloat(), dataDetailWeathers[i].temperature.toFloat()))
                j += 1
            }

            val set1 = LineDataSet(yTValues, "Temperature")
            set1.color = Color.parseColor("#29B6FC")
            set1.lineWidth = 3f
            set1.valueTextSize = 12f
            set1.setDrawFilled(true)
            set1.fillAlpha = 255
            set1.isHighlightEnabled = false
            set1.fillColor = Color.parseColor("#29B6FC")
            set1.mode = LineDataSet.Mode.CUBIC_BEZIER
            set1.setCircleColor(Color.BLACK)
            //set1.setCircleColorHole(Color.BLACK)
            set1.circleRadius = 4f

            val dataSets: ArrayList<ILineDataSet> = ArrayList()
            dataSets.add(set1)

            val data = LineData(dataSets)

            lineChart.data = data
        }
    }
}