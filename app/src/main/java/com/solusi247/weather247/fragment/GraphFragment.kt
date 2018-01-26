package com.solusi247.weather247.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.solusi247.weather247.R
import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.module.presenter.GraphPresenter
import com.solusi247.weather247.module.view.GraphView
import kotlinx.android.synthetic.main.fragment_graph.*


class GraphFragment : Fragment(), GraphView {

    lateinit var dataDetailWeathers: List<ResponseModel.DataDetailWeather>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_graph, container, false)

        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val presenter = GraphPresenter(this)

        chartTemperature.apply {
            isDragEnabled = true
            isScaleYEnabled = false
            animateXY(2000, 2000)
            setScaleMinima(3f, 0f)
            description.isEnabled = false
            axisLeft.axisMinimum = 10f
            axisLeft.axisMaximum = 40f
            axisRight.isEnabled = false
            xAxis.granularity = 1f
        }

        chartPressure.apply {
            isDragEnabled = true
            isScaleYEnabled = false
            animateXY(2000, 2000)
            setScaleMinima(3f, 0f)
            description.isEnabled = false
            axisLeft.axisMinimum = 800f
            axisLeft.axisMaximum = 1200f
            axisRight.isEnabled = false
            xAxis.granularity = 1f
        }

        chartHumidity.apply {
            isDragEnabled = true
            isScaleYEnabled = false
            animateXY(2000, 2000)
            setScaleMinima(3f, 0f)
            description.isEnabled = false
            axisLeft.axisMinimum = 0f
            axisLeft.axisMaximum = 100f
            axisRight.isEnabled = false
            xAxis.granularity = 1f
        }

        presenter.loadGraphTemperature(dataDetailWeathers)
        presenter.loadGraphPressure(dataDetailWeathers)
        presenter.loadGraphHumidity(dataDetailWeathers)
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        inflater.inflate(R.menu.graph_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val weatherFragment = WeatherFragment()
        weatherFragment.dataDetailWeathers = dataDetailWeathers
        when (item.itemId) {
            R.id.actionList ->
                fragmentManager!!.beginTransaction()
                        .setCustomAnimations(
                                R.animator.detail_flip_right_in,
                                R.animator.detail_flip_right_out)
                        .replace(R.id.container, weatherFragment)
                        .commit()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onGraphTemperature(temperatures: List<String>) {

        val entries: ArrayList<Entry> = ArrayList()

        for (i in temperatures.indices) {
            entries.add(Entry(i.toFloat(), temperatures[i].toFloat()))
        }

        val set1 = LineDataSet(entries, getString(R.string.temperature)).apply {
            color = Color.parseColor("#29B6FC")
            lineWidth = 3f
            valueTextSize = 12f
            setDrawFilled(true)
            fillAlpha = 255
            isHighlightEnabled = false
            fillColor = Color.parseColor("#29B6FC")
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setCircleColor(Color.BLACK)
            circleRadius = 4f
        }

        val dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(set1)

        val data = LineData(dataSets)

        chartTemperature.data = data
    }

    override fun onGraphPressure(pressure: List<String>) {
        val entries: ArrayList<Entry> = ArrayList()

        for (i in pressure.indices) {
            entries.add(Entry(i.toFloat(), pressure[i].toFloat()))
        }

        val set1 = LineDataSet(entries, getString(R.string.pressure)).apply {
            color = Color.parseColor("#29B6FC")
            lineWidth = 3f
            valueTextSize = 12f
            setDrawFilled(true)
            fillAlpha = 255
            isHighlightEnabled = false
            fillColor = Color.parseColor("#29B6FC")
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setCircleColor(Color.BLACK)
            circleRadius = 4f
        }

        val dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(set1)

        val data = LineData(dataSets)

        chartPressure.data = data
    }

    override fun onGraphHumidity(humidity: List<String>) {
        val entries: ArrayList<Entry> = ArrayList()

        for (i in humidity.indices) {
            entries.add(Entry(i.toFloat(), humidity[i].toFloat()))
        }

        val set1 = LineDataSet(entries, getString(R.string.humidity)).apply {
            color = Color.parseColor("#29B6FC")
            lineWidth = 3f
            valueTextSize = 12f
            setDrawFilled(true)
            fillAlpha = 255
            isHighlightEnabled = false
            fillColor = Color.parseColor("#29B6FC")
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setCircleColor(Color.BLACK)
            circleRadius = 4f
        }

        val dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(set1)

        val data = LineData(dataSets)

        chartHumidity.data = data
    }

}
