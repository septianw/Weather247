package com.solusi247.weather247.adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.solusi247.weather247.R
import com.solusi247.weather247.Weather247
import com.solusi247.weather247.listener.ListWeatherListener
import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.utils.convertToWeatherIcon
import com.solusi247.weather247.utils.convertToWhiteWeatherIcon
import kotlinx.android.synthetic.main.list_weather_item.view.*

class WeatherAdapter(val dataDetailWeathers: List<ResponseModel.DataDetailWeather>,
                     val listener: ListWeatherListener)
    : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    var detailPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_weather_item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val isDetailed = position == detailPosition
        with(holder) {
            bind(dataDetailWeathers[position], isDetailed)
            listWeather.isActivated = isDetailed
            listWeather.setOnClickListener {
                notifyItemChanged(detailPosition)
                notifyItemChanged(position)
                listener.onListClicked(dataDetailWeathers.get(position))
                detailPosition = position
            }
        }
    }

    override fun getItemCount() = dataDetailWeathers.size

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listWeather: ConstraintLayout
        val tvTime: TextView
        val ivIconWeather: ImageView
        val ivTemperature: ImageView
        val tvTemperature: TextView

        init {
            listWeather = itemView.listWeather
            tvTime = itemView.tvTime
            ivIconWeather = itemView.ivIconWeather
            ivTemperature = itemView.ivTemperature
            tvTemperature = itemView.tvTemperature
        }

        fun bind(dataDetailWeather: ResponseModel.DataDetailWeather, inverted: Boolean) {
            tvTime.text = dataDetailWeather.time
            ivIconWeather.setImageResource(
                    if (inverted) dataDetailWeather.weather.convertToWhiteWeatherIcon()
                    else dataDetailWeather.weather.convertToWeatherIcon())
            ivTemperature.setImageResource(
                    if (inverted) R.drawable.ic_temperature_white
                    else R.drawable.ic_temperature)
            tvTemperature.text = String.format(Weather247.context.getString(R.string.temperature_text, dataDetailWeather.temperature))
        }
    }
}