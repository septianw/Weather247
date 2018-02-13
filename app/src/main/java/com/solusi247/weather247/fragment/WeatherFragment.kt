package com.solusi247.weather247.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.solusi247.weather247.R
import com.solusi247.weather247.adapter.WeatherAdapter
import com.solusi247.weather247.listener.ListWeatherListener
import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.module.presenter.WeatherPresenter
import com.solusi247.weather247.module.view.WeatherView
import com.solusi247.weather247.utils.convertToWhiteLargeWeatherIcon
import com.solusi247.weather247.utils.textAnimationIncrement
import kotlinx.android.synthetic.main.fragment_weather.*

class WeatherFragment : Fragment(), WeatherView, ListWeatherListener {

    lateinit var fadeIn: Animation

    lateinit var dataDetailWeathers: List<ResponseModel.DataDetailWeather>

    /*************************************************************************************/
    /****************************   Override Function Fragment   *************************/
    /*************************************************************************************/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        // Set has option menu to true because it has an option menu in this fragment
        setHasOptionsMenu(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Init presenter
        val presenter = WeatherPresenter(this)

        fadeIn = AnimationUtils.loadAnimation(activity!!.baseContext, R.anim.fade_in)

        // Declare layout manager for recyclerview
        val layoutManager = LinearLayoutManager(activity!!.baseContext)
        rvWeathers.layoutManager = layoutManager

        // Add a divider for recyclerview
        val dividerItem = DividerItemDecoration(rvWeathers.context, layoutManager.orientation)
        dividerItem.setDrawable(ContextCompat.getDrawable(activity!!.baseContext, R.drawable.divider_item)!!)
        rvWeathers.addItemDecoration(dividerItem)


        // Init weather details
        presenter.initWeatherDetails(dataDetailWeathers)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        inflater.inflate(R.menu.weather_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val graphFragment = GraphFragment()
        graphFragment.dataDetailWeathers = dataDetailWeathers
        when (item.itemId) {
            R.id.actionGraphic ->
                fragmentManager!!.beginTransaction()
                        .setCustomAnimations(
                                R.animator.detail_flip_right_in,
                                R.animator.detail_flip_right_out)
                        .replace(R.id.container, graphFragment)
                        .commit()
        }
        return super.onOptionsItemSelected(item)
    }
    /*****************************End of Override Function Fragment************************/


    /***************************************************************************************/
    /**************************************   View   ***************************************/
    /***************************************************************************************/

    override fun onListWeather(dataDetailWeathers: List<ResponseModel.DataDetailWeather>) {
        rvWeathers.adapter = WeatherAdapter(dataDetailWeathers, this)
    }
    /************************************** End of View ************************************/


    /*************************************************************************************/
    /******************************   ListWeatherListener   ******************************/
    /*************************************************************************************/
    override fun onListClicked(dataDetailWeather: ResponseModel.DataDetailWeather) {
        ivIconWeather.startAnimation(fadeIn)
        ivIconWeather.visibility = View.VISIBLE
        ivIconWeather.setImageResource(dataDetailWeather.weather.convertToWhiteLargeWeatherIcon())
        tvDescription.startAnimation(fadeIn)
        tvDescription.visibility = View.VISIBLE
        tvDescription.text = dataDetailWeather.weather
        tvTemperature.textAnimationIncrement(dataDetailWeather.temperature, 1000, "\u00b0")
        tvHumidity.textAnimationIncrement(dataDetailWeather.humidity, 1000, "%")
        tvPressure.textAnimationIncrement(dataDetailWeather.pressure, 1000, "hPa")
    }
    /****************************** End of ListWeatherListener ******************************/
}