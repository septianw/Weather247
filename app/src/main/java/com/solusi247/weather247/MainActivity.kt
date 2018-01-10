package com.solusi247.weather247

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.solusi247.weather247.model.ResponseModel
import com.solusi247.weather247.service.ApiService
import com.solusi247.weather247.utils.Constant
import com.solusi247.weather247.utils.Message
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainListener, LastWeatherListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Declare activity presenter
        val presenter = MainPresenter(this)

        supportActionBar?.elevation = 1f

        rvLastWeather.adapter = MainAdapter(this, this)
        val gridLayoutManager = GridLayoutManager(this, 3)
        rvLastWeather.layoutManager = gridLayoutManager

        val apiService = ApiService.create()

        apiService.getAllWeather()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { result ->
                            Message.showToast(this, Constant.PROBLEM_SERVER)
                            if (!result.error) setData(result.data[0]) else Message.showToast(this, result.message)
                        },
                        { error ->
                            error.printStackTrace()
                            Message.showToast(this, Constant.PROBLEM_SERVER)
                        }
                )

        clWeatherNow.setOnClickListener { goToDetail() }
    }

    /******************************Last Weather Listener*************************/
    override fun goToDetail() {
        startActivity(Intent(this, DetailActivity::class.java))
    }

    /***************************End of Last Weather Listener*********************/

    fun setData(model: ResponseModel.DataWeather) {
        val temperature = "${model.temperature}\u2103"
        ivIconWeather.setImageResource(
                when (model.weather) {
                    "Thunderstorm" -> R.drawable.ic_thunderstorm_white
                    "Cloudy" -> R.drawable.ic_cloudy_white
                    "Partly Cloudy" -> R.drawable.ic_partly_cloudy_white
                    "Sunny" -> R.drawable.ic_sunny
                    "Rainy" -> R.drawable.ic_rainy_white
                    else -> 0
                }
        )
        tvDescription.text = model.weather
        tvTemperature.text = model.temperature
        tvPressure.text = model.pressure
        tvHumidity.text = model.humidity
    }

}
