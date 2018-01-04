package com.solusi247.weather247

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class DetailAdapter(val context: Context) : RecyclerView.Adapter<DetailAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_weather_item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        if (position == 0) {
            holder.ivIconWeather.setImageResource(R.drawable.ic_thunderstorm_white)
            holder.tvDescription.text = "Thunderstorm"
        } else if (position == 1) {
            holder.ivIconWeather.setImageResource(R.drawable.ic_cloudy_white)
            holder.tvDescription.text = "Cloudy"
        } else if (position == 2) {
            holder.ivIconWeather.setImageResource(R.drawable.ic_partly_cloudy_white)
            holder.tvDescription.text = "Partly Cloudy"
        } else if (position == 3) {
            holder.ivIconWeather.setImageResource(R.drawable.ic_sunny)
            holder.tvDescription.text = "Sunny"
        } else if (position == 4) {
            holder.ivIconWeather.setImageResource(R.drawable.ic_rainy_white)
            holder.tvDescription.text = "Rainy"
        }
        holder.tvTime.text = "${20 - position}:00"
        holder.tvDate.text = "Saturday\n21/12/2018"
        holder.tvTemperature.text = "28\u2103"
        holder.tvPressure.text = "998 hPa"
        holder.tvHumidity.text = "98 %"

        if (position % 2 == 0)
            holder.listWeather.setBackgroundColor(ContextCompat.getColor(context, R.color.bgListEven))
        else
            holder.listWeather.setBackgroundColor(ContextCompat.getColor(context, R.color.bgListOdd))

    }

    override fun getItemCount() = 5

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
            listWeather = itemView.findViewById(R.id.list_weather)
            ivIconWeather = itemView.findViewById(R.id.iv_icon_weather)
            tvDescription = itemView.findViewById(R.id.tv_description)
            tvTime = itemView.findViewById(R.id.tv_time)
            tvDate = itemView.findViewById(R.id.tv_date)
            tvTemperature = itemView.findViewById(R.id.tv_temperature)
            tvPressure = itemView.findViewById(R.id.tv_pressure)
            tvHumidity = itemView.findViewById(R.id.tv_humidity)

        }
    }
}