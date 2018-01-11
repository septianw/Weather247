package com.solusi247.weather247.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.solusi247.weather247.R
import com.solusi247.weather247.adapter.MainAdapter
import com.solusi247.weather247.listener.LastWeatherListener
import com.solusi247.weather247.model.ResponseModel
import com.solusi247.weather247.presenter.MainPresenter
import com.solusi247.weather247.utils.convertToWeatherIconWhite
import com.solusi247.weather247.view.MainView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView, LastWeatherListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /********************************Set layout************************************/
        supportActionBar?.elevation = 1f

        rvLastWeather.layoutManager = GridLayoutManager(this, 3)
        /****************************End of set layout**********************************/


        // Declare activity presenter
        val presenter = MainPresenter(this)

        // Presenter load weather
        presenter.loadWeather()


    }

    /******************************************View***************************************/
    override fun onWeatherToday(dataWeather: ResponseModel.DataWeather) {
        ivIconWeather.setImageResource(dataWeather.weather.convertToWeatherIconWhite())
        tvDescription.text = dataWeather.weather
        tvTemperature.text = dataWeather.temperature
        tvPressure.text = dataWeather.pressure
        tvHumidity.text = dataWeather.humidity
        clWeatherNow.setOnClickListener {
            goToDetail(dataWeather.date)
        }
    }

    override fun onLastWeather(dataWeathers: List<ResponseModel.DataWeather>) {
        val lastWeatherString = "Last ${dataWeathers.size - 1} weather(s) info"
        lastWeather.text = lastWeatherString
        rvLastWeather.adapter = MainAdapter(this, dataWeathers, this)
    }
    /***************************************End of View************************************/


    /******************************Last Weather Listener*************************/
    override fun goToDetail(date: String) {
        val intentToDetail = Intent(this, DetailActivity::class.java)
        intentToDetail.putExtra("date", date)
        startActivity(intentToDetail)
    }
    /***************************End of Last Weather Listener*********************/
}
