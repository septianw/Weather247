package com.solusi247.weather247.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.solusi247.weather247.R
import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.utils.addUnitHumidity
import com.solusi247.weather247.utils.addUnitPressure
import com.solusi247.weather247.utils.addUnitTemperature
import com.solusi247.weather247.utils.convertToWeatherIconWhite
import kotlinx.android.synthetic.main.list_weather_item.view.*

class DetailAdapter(val context: Context,
                    val dataDetailWeathers: List<ResponseModel.DataDetailWeather>)
    : RecyclerView.Adapter<DetailAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_weather_item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        with(holder) {
            bind(dataDetailWeathers[position])
            listWeather.setBackgroundColor(if (position % 2 == 1)
                ContextCompat.getColor(context, R.color.bgListOdd)
            else
                ContextCompat.getColor(context, R.color.bgListEven)
            )
        }
    }

    override fun getItemCount() = dataDetailWeathers.size

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

        fun bind(dataDetailWeather: ResponseModel.DataDetailWeather) {
            ivIconWeather.setImageResource(dataDetailWeather.weather.convertToWeatherIconWhite())
            tvDescription.text = dataDetailWeather.weather
            tvTime.text = dataDetailWeather.time
            tvDate.text = String.format(itemView.context.getString(R.string.date), dataDetailWeather.day, dataDetailWeather.date)
            tvTemperature.text = dataDetailWeather.temperature.addUnitTemperature()
            tvPressure.text = dataDetailWeather.pressure.addUnitPressure()
            tvHumidity.text = dataDetailWeather.humidity.addUnitHumidity()
        }
    }
}