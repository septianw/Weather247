package com.solusi247.weather247.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.solusi247.weather247.R
import com.solusi247.weather247.model.ResponseModel
import com.solusi247.weather247.utils.convertToWeatherIconWhite
import kotlinx.android.synthetic.main.list_weather_item.view.*

/**
 * Created by aldidwikip on 11/01/2018.
 */
class DetailAdapter(val context: Context, val dataDetailWeathers: List<ResponseModel.DataDetailWeather>) :
        RecyclerView.Adapter<DetailAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_weather_item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        with(holder) {
            bind(dataDetailWeathers[position])
        }
    }

    override fun getItemCount(): Int {
        return dataDetailWeathers.size
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listWeather: ConstraintLayout
        val ivIconWeather: ImageView
        val tvDescription: TextView
        val tvTime: TextView
        val tvDate: TextView
        val tvTemperature: TextView
        val tvPressure: TextView
        val tvHumidity: TextView

        init {
            listWeather = itemView.listWeather
            ivIconWeather = itemView.ivIconWeather
            tvDescription = itemView.tvDescription
            tvTime = itemView.tvTime
            tvDate = itemView.tvDate
            tvTemperature = itemView.tvTemperature
            tvPressure = itemView.tvPressure
            tvHumidity = itemView.tvHumidity
        }

        fun bind(dataDetailWeathers: ResponseModel.DataDetailWeather) {
            ivIconWeather.setImageResource(dataDetailWeathers.weather.convertToWeatherIconWhite())
            tvDescription.text = dataDetailWeathers.weather
            tvTime.text = dataDetailWeathers.time
            tvDate.text = dataDetailWeathers.day + "\n" + dataDetailWeathers.date
            tvTemperature.text = dataDetailWeathers.temperature + "\u2103"
            tvPressure.text = dataDetailWeathers.pressure + " hPa"
            tvHumidity.text = dataDetailWeathers.humidity + " %"
        }
    }
}