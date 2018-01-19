package com.solusi247.weather247.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.solusi247.weather247.R
import com.solusi247.weather247.adapter.DetailAdapter
import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.module.presenter.WeatherPresenter
import com.solusi247.weather247.module.view.WeatherView
import kotlinx.android.synthetic.main.fragment_weather.*

class WeatherFragment : Fragment(), WeatherView {

    lateinit var dataDetailWeathers: List<ResponseModel.DataDetailWeather>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        // Set has option menu to true because it has an option menu in this fragment
        setHasOptionsMenu(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Declare layout manager for recyclerview
        val layoutManager = LinearLayoutManager(activity!!.baseContext)
        rvWeathers.layoutManager = layoutManager

        // Add a divider for recyclerview
        val dividerItem = DividerItemDecoration(rvWeathers.context, layoutManager.orientation)
        dividerItem.setDrawable(ContextCompat.getDrawable(activity!!.baseContext, R.drawable.divider_item)!!)
        rvWeathers.addItemDecoration(dividerItem)

        // Init presenter
        val presenter = WeatherPresenter(this)

        // Init weather details
        presenter.initWeatherDetails(dataDetailWeathers)
    }

    /***************************************************************************************/
    /**************************************   View   ***************************************/
    /***************************************************************************************/

    override fun onListWeather(dataDetailWeathers: List<ResponseModel.DataDetailWeather>) {
        rvWeathers.adapter = DetailAdapter(activity!!.baseContext, dataDetailWeathers, rvWeathers)
    }

    /***************************************End of View************************************/


    /***************************************************************************************/
    /****************************   Override Function Fragment   ***************************/
    /***************************************************************************************/

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        inflater.inflate(R.menu.weather_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionGraphic ->
                fragmentManager!!.beginTransaction()
                        .setCustomAnimations(
                                R.animator.detail_flip_right_in,
                                R.animator.detail_flip_right_out)
                        .replace(R.id.container, GraphFragment())
                        .commit()
        }
        return super.onOptionsItemSelected(item)
    }
    /*****************************End of Override Function Fragment**************************/

}