package com.solusi247.weather247.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.solusi247.weather247.R
import com.solusi247.weather247.custom.CustomMarkerView
import com.solusi247.weather247.custom.CustomXAxisValueFormater
import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.module.presenter.GraphPresenter
import com.solusi247.weather247.module.view.GraphView
import kotlinx.android.synthetic.main.fragment_graph.*


class GraphFragment : Fragment(), GraphView {

    lateinit var dataDetailWeathers: List<ResponseModel.DataDetailWeather>

    /************************************************************************************/
    /***************************   Override Function Fragment   *************************/
    /************************************************************************************/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_graph, container, false)

        // Set has option menu to true because it has an option menu in this fragment
        setHasOptionsMenu(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val presenter = GraphPresenter(this)
        val mv = CustomMarkerView(activity!!.baseContext, R.layout.custom_marker_view)

        mv.apply {
            chartView = chartTemperature
            chartView = chartHumidity
            chartView = chartPressure
        }

        chartTemperature.apply {
            marker = mv
            legend.isEnabled = false
            isScaleYEnabled = false
            animateXY(1000, 1000)
            description.isEnabled = false
            xAxis.setLabelCount(5)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setValueFormatter(CustomXAxisValueFormater(dataDetailWeathers.map { it.time }))
            axisRight.isEnabled = false
        }

        chartPressure.apply {
            marker = mv
            legend.isEnabled = false
            isScaleYEnabled = false
            animateXY(1000, 1000)
            description.isEnabled = false
            xAxis.setLabelCount(5)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setValueFormatter(CustomXAxisValueFormater(dataDetailWeathers.map { it.time }))
            axisRight.isEnabled = false
        }

        chartHumidity.apply {
            marker = mv
            legend.isEnabled = false
            isScaleYEnabled = false
            animateXY(1000, 1000)
            description.isEnabled = false
            xAxis.setLabelCount(5)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setValueFormatter(CustomXAxisValueFormater(dataDetailWeathers.map { it.time }))
            axisLeft.granularity = 5f
            axisRight.isEnabled = false
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
    /****************************End of Override Function Fragment************************/


    /*************************************************************************************/
    /**************************************   View   *************************************/
    /*************************************************************************************/

    override fun onGraphTemperature(lineDataTemperature: LineData) {
        chartTemperature.data = lineDataTemperature
    }

    override fun onGraphPressure(lineDataPressure: LineData) {
        chartPressure.data = lineDataPressure
    }

    override fun onGraphHumidity(lineDataHumidity: LineData) {
        chartHumidity.data = lineDataHumidity
    }
    /***************************************End of View************************************/

}
