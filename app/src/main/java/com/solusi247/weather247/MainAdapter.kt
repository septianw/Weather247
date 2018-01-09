package com.solusi247.weather247

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.last_weather_item.view.*

class MainAdapter(val context: Context, val listener: LastWeatherListener) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.last_weather_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        if (position == 0) {
            holder.ivIconWeather.setImageResource(R.drawable.ic_cloudy)
            holder.tvDay.text = "Thursday"
        } else if (position == 1) {
            holder.ivIconWeather.setImageResource(R.drawable.ic_thunderstorm)
            holder.tvDay.text = "Wednesday"
        } else if (position == 2) {
            holder.ivIconWeather.setImageResource(R.drawable.ic_sunny)
            holder.tvDay.text = "Tuesday"
        } else if (position == 3) {
            holder.ivIconWeather.setImageResource(R.drawable.ic_partly_cloudy)
            holder.tvDay.text = "Monday"
        } else if (position == 4) {
            holder.ivIconWeather.setImageResource(R.drawable.ic_thunderstorm)
            holder.tvDay.text = "Sunday"
        } else if (position == 5) {
            holder.ivIconWeather.setImageResource(R.drawable.ic_rainy)
            holder.tvDay.text = "Saturday"
        }
        holder.cvWeather.setOnClickListener { listener.goToDetail() }
    }

    override fun getItemCount(): Int {
        return 6
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvWeather: CardView
        val ivIconWeather: ImageView
        val tvDay: TextView

        init {
            cvWeather = itemView.cvWeatherItem
            ivIconWeather = itemView.ivIconWeather
            tvDay = itemView.tvDay
        }
    }

}