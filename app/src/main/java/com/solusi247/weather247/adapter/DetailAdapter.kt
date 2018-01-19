package com.solusi247.weather247.adapter

import android.content.Context
import android.support.transition.TransitionManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.solusi247.weather247.R
import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.utils.convertToWeatherIcon
import kotlinx.android.synthetic.main.list_weather_item.view.*

class DetailAdapter(val context: Context,
                    val dataDetailWeathers: List<ResponseModel.DataDetailWeather>,
                    val recyclerView: RecyclerView)
    : RecyclerView.Adapter<DetailAdapter.WeatherViewHolder>() {

    var expandedPostition = -1

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_weather_item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val isExpanded = position == expandedPostition
        with(holder) {
            bind(dataDetailWeathers[position])
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//                llWeather.stateListAnimator = AnimatorInflater.loadStateListAnimator(itemView.context, R.animator.detail_selection)
            llWeather.isActivated = isExpanded
            attrWeather.visibility = if (isExpanded) View.VISIBLE else View.GONE
            llWeather.setOnClickListener { _ ->
                notifyItemChanged(position)
                notifyItemChanged(expandedPostition)
                expandedPostition = if (isExpanded) -1 else position
                TransitionManager.beginDelayedTransition(recyclerView)
            }
        }
    }

    override fun getItemCount() = dataDetailWeathers.size

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val llWeather: LinearLayout
        val tvDescription: TextView
        val ivIconWeather: ImageView
        val tvTime: TextView
        val attrWeather: LinearLayout
        val tvTemperature: TextView
        val tvPressure: TextView
        val tvHumidity: TextView

        init {
            llWeather = itemView.llWeather
            tvDescription = itemView.tvDescription
            ivIconWeather = itemView.ivIconWeather
            tvTime = itemView.tvTime
            attrWeather = itemView.attrWeather
            tvTemperature = itemView.tvTemperature
            tvPressure = itemView.tvPressure
            tvHumidity = itemView.tvHumidity
        }

        fun bind(dataDetailWeather: ResponseModel.DataDetailWeather) {
            ivIconWeather.setImageResource(dataDetailWeather.weather.convertToWeatherIcon())
            tvDescription.text = dataDetailWeather.weather
            tvTime.text = dataDetailWeather.time
            tvTemperature.text = String.format(itemView.context.getString(R.string.temperature_text), dataDetailWeather.temperature)
            tvPressure.text = String.format(itemView.context.getString(R.string.pressure_text), dataDetailWeather.pressure)
            tvHumidity.text = String.format(itemView.context.getString(R.string.humidity_text), dataDetailWeather.humidity)
        }
    }
}