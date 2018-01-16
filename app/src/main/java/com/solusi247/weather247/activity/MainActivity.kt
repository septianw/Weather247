package com.solusi247.weather247.activity

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.solusi247.weather247.R
import com.solusi247.weather247.adapter.MainAdapter
import com.solusi247.weather247.listener.AttrWeatherListener
import com.solusi247.weather247.listener.LastWeatherListener
import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.module.presenter.MainPresenter
import com.solusi247.weather247.module.view.MainView
import com.solusi247.weather247.utils.Message
import com.solusi247.weather247.utils.changeFormatDate
import com.solusi247.weather247.utils.convertToLargeWeatherIcon
import com.solusi247.weather247.utils.textAnimationIncrement
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView, LastWeatherListener, AttrWeatherListener {

    lateinit var fadeIn: Animation
    lateinit var moveUp: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        moveUp = AnimationUtils.loadAnimation(this, R.anim.move_up)

        /********************************Set layout************************************/
        val linearLayoutManager = LinearLayoutManager(this)

        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        rvLastWeather.layoutManager = linearLayoutManager
        /****************************End of set layout**********************************/


        // Declare activity presenter
        val presenter = MainPresenter(this)

        // Presenter load weather
        presenter.loadWeather()

    }

    /***************************************************************************************/
    /**************************************   View   ***************************************/
    /***************************************************************************************/

    override fun showLoading() {
        pbLoading.setBackgroundResource(R.drawable.animation_drawable)
        val anim = pbLoading.background as AnimationDrawable
        val zoom = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_animation)
        pbLoading.animation = zoom
        anim.start()
    }

    override fun hideLoading() {
        pbLoading.clearAnimation()
        pbLoading.visibility = View.INVISIBLE
    }

    override fun playAnimationWeatherToday() {
        ivIconWeather.startAnimation(fadeIn)
        tvDescription.startAnimation(fadeIn)
        tvLocation.startAnimation(fadeIn)
        tvDate.startAnimation(fadeIn)
        tvTemperature.startAnimation(fadeIn)
        tvPressure.startAnimation(fadeIn)
        tvHumidity.startAnimation(fadeIn)
        llWeatherInfo.startAnimation(moveUp)
        tvTemperature.visibility = View.VISIBLE
        tvPressure.visibility = View.VISIBLE
        tvHumidity.visibility = View.VISIBLE
        llWeatherInfo.visibility = View.VISIBLE
    }

    override fun onWeatherToday(dataWeather: ResponseModel.DataWeather) {
        ivIconWeather.setImageResource(dataWeather.weather.convertToLargeWeatherIcon())
        tvDescription.text = dataWeather.weather
        tvLocation.text = getString(R.string.default_location)
        tvDate.text = String.format(getString(R.string.last_update), dataWeather.date.changeFormatDate(), dataWeather.time)
        tvTemperature.textAnimationIncrement(dataWeather.temperature, "\u2103")
        tvPressure.textAnimationIncrement(dataWeather.pressure, "hPa")
        tvHumidity.textAnimationIncrement(dataWeather.humidity, "%")
        tvTemperature.setOnClickListener { onTemperatureClicked() }
        tvPressure.setOnClickListener { onPressureClicked() }
        tvHumidity.setOnClickListener { onHumidityClicked() }
    }

    override fun onLastWeather(dataWeathers: List<ResponseModel.DataWeather>) {
        lastWeather.text = getString(R.string.past_weather)
        rvLastWeather.adapter = MainAdapter(this, dataWeathers, this)
    }
    /***************************************End of View************************************/


    /***************************************************************************************/
    /*****************************   Last Weather Listener   *******************************/
    /***************************************************************************************/

    override fun goToDetail(date: String) {
        val intentToDetail = Intent(this, DetailActivity::class.java)
        intentToDetail.putExtra("date", date)
        startActivity(intentToDetail)
    }
    /******************************End of Last Weather Listener******************************/


    /***************************************************************************************/
    /*****************************   Attr Weather Listener   *******************************/
    /***************************************************************************************/

    override fun onTemperatureClicked() {
        val message = "Temperature now is ${tvTemperature.text}"
        Message.showToast(this, message, Message.INFORMATION)
    }

    override fun onHumidityClicked() {
        val message = "Humidity now is ${tvHumidity.text}"
        Message.showToast(this, message, Message.INFORMATION)
    }

    override fun onPressureClicked() {
        val message = "Pressure now is ${tvPressure.text}"
        Message.showToast(this, message, Message.INFORMATION)
    }
    /******************************End of Attr Weather Listener******************************/
}
