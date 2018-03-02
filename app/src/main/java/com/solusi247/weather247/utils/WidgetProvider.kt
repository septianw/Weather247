package com.solusi247.weather247.utils

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.RemoteViews
import com.solusi247.weather247.R
import com.solusi247.weather247.activity.MainActivity
import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.module.presenter.WidgetPresenter
import com.solusi247.weather247.module.view.WidgetView
import com.solusi247.weather247.utils.WeatherUtils.convertToWhiteWeatherIcon


class WidgetProvider : AppWidgetProvider(), WidgetView {

    lateinit var context: Context
    lateinit var widgetId: IntArray

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {

        this.context = context
        widgetId = appWidgetIds

        val presenter = WidgetPresenter(this)

        presenter.loadWeatherNow()
    }

    override fun onDataLoaded(dataWeather: ResponseModel.DataWeather) {
        val remoteViews = RemoteViews(context.getPackageName(), R.layout.widget_weather).apply {

            setTextViewText(R.id.tvLocation, context.getString(R.string.default_location))
            setTextViewText(R.id.tvDescription, dataWeather.weather)
            setTextViewText(R.id.tvTemperature, String.format(context.getString(R.string.temperature_text), dataWeather.temperature))
            setImageViewResource(R.id.ivIconWeather, dataWeather.weather.convertToWhiteWeatherIcon())
            setViewVisibility(R.id.pbLoading, View.GONE)

            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            setOnClickPendingIntent(R.id.rlWidget, pendingIntent)
        }
        AppWidgetManager.getInstance(context).updateAppWidget(widgetId, remoteViews)
    }
}