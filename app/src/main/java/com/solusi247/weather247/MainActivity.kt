package com.solusi247.weather247

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.solusi247.weather247.model.DataDetailWeather
import com.solusi247.weather247.service.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity(), LastWeatherListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.elevation = 1f

        val clWeatherNow = findViewById<ConstraintLayout>(R.id.cl_weather_now)
        val rvLastWeather = findViewById<RecyclerView>(R.id.rv_last_weather)

        rvLastWeather.adapter = MainAdapter(this, this)
        val gridLayoutManager = GridLayoutManager(this, 3)
        rvLastWeather.layoutManager = gridLayoutManager

        val apiService =  ApiService.create()

        apiService.getAllWeather()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { result ->
                            if (!result.error) setData(result.data[0]) else showToast(result.message)
                        },
                        { t ->
                            t.printStackTrace()
                            showToast("Error server")
                        }
                )

        clWeatherNow.setOnClickListener { goToDetail() }
    }

    override fun goToDetail() {
        startActivity(Intent(this, DetailActivity::class.java))
    }

    fun setData(model: DataDetailWeather) {
        val ivIconWeather = findViewById<ImageView>(R.id.iv_icon_weather)
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
        val tvDescription = findViewById<TextView>(R.id.tv_description)
        tvDescription.text = model.weather
        val tvTemperature = findViewById<TextView>(R.id.tv_temperature)
        tvTemperature.text = "${model.temperature}\u2103"
        val tvPressure = findViewById<TextView>(R.id.tv_pressure)
        tvPressure.text = "${model.pressure}hPa"
        val tvHumidity = findViewById<TextView>(R.id.tv_humidity)
        tvHumidity.text = "${model.humidity}%"
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
