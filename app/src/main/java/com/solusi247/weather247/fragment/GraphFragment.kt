package com.solusi247.weather247.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.solusi247.weather247.R
import com.solusi247.weather247.adapter.GraphAdapter
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

        // Declare layout manager for recyclerview
        val layoutManager = LinearLayoutManager(activity!!.baseContext)
        rvGraphs.layoutManager = layoutManager

        val presenter = GraphPresenter(this)
        presenter.initGraphDetails(dataDetailWeathers)
    }

    override fun onGraphWeather(dataDetailWeathers: List<ResponseModel.DataDetailWeather>) {
        rvGraphs.adapter = GraphAdapter(dataDetailWeathers)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        inflater.inflate(R.menu.graph_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onGraphLoaded(item, dataDetailWeathers)
        return super.onOptionsItemSelected(item)
    }

    fun onGraphLoaded(item: MenuItem, dataDetailWeathers: List<ResponseModel.DataDetailWeather>) {
        val weatherFragment = WeatherFragment()
        weatherFragment.dataDetailWeathers = dataDetailWeathers
        when (item.itemId) {
            R.id.actionList->
                fragmentManager!!.beginTransaction()
                        .setCustomAnimations(
                                R.animator.detail_flip_right_in,
                                R.animator.detail_flip_right_out)
                        .replace(R.id.container, weatherFragment)
                        .commit()
        }
    }
}
