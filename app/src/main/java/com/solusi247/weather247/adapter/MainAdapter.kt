package com.solusi247.weather247.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.solusi247.weather247.R
import com.solusi247.weather247.listener.LastWeatherListener
import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.utils.convertToWeatherIcon
import kotlinx.android.synthetic.main.last_weather_item.view.*

class MainAdapter(val context: Context,
                  val dataWeathers: List<ResponseModel.DataWeather>,
                  val listener: LastWeatherListener)
    : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.last_weather_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        with(holder) {
            bind(dataWeathers[position + 1])
            cvWeather.setOnClickListener { listener.goToDetail(dataWeathers[position + 1].date) }
        }
    }

    override fun getItemCount() = dataWeathers.size - 1

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvWeather: CardView
        val ivIconWeather: ImageView
        val tvDay: TextView

        init {
            cvWeather = itemView.cvWeatherItem
            ivIconWeather = itemView.ivIconWeather
            tvDay = itemView.tvDay
        }

        fun bind(dataWeather: ResponseModel.DataWeather) {
            ivIconWeather.setImageResource(dataWeather.weather.convertToWeatherIcon())
            tvDay.text = dataWeather.day
        }
    }

}