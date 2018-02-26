package com.solusi247.weather247.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.solusi247.weather247.R
import com.solusi247.weather247.Weather247
import com.solusi247.weather247.listener.LastWeatherListener
import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.utils.changeDateToLong
import com.solusi247.weather247.utils.changeFormatDate
import com.solusi247.weather247.utils.convertToWhiteWeatherIcon
import kotlinx.android.synthetic.main.last_weather_item.view.*

class MainAdapter(val dataWeathers: List<ResponseModel.DataWeather>,
                  val listener: LastWeatherListener)
    : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.last_weather_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        with(holder) {
            bind(dataWeathers[position])
            if (DateUtils.isToday(dataWeathers[position].date.changeDateToLong())) {
                itemView.setBackgroundColor(ContextCompat.getColor(Weather247.context, R.color.todayDetail))
            }
        }
    }

    override fun getItemCount() = dataWeathers.size

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivIconWeather: ImageView
        val tvDate: TextView

        init {
            ivIconWeather = itemView.ivIconWeather
            tvDate = itemView.tvDate
        }

        fun bind(dataWeather: ResponseModel.DataWeather) {
            ivIconWeather.setImageResource(dataWeather.weather.convertToWhiteWeatherIcon())
            tvDate.text = dataWeather.date.changeFormatDate()
            itemView.setOnClickListener { listener.goToDetail(dataWeather.day, dataWeather.date) }
        }
    }

}