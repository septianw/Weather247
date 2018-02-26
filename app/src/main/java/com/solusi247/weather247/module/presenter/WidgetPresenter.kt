package com.solusi247.weather247.module.presenter

import android.text.format.DateUtils
import com.solusi247.weather247.module.view.WidgetView
import com.solusi247.weather247.service.ApiService
import com.solusi247.weather247.utils.changeDateToLong
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WidgetPresenter(val view: WidgetView) {

    val apiService: ApiService

    init {
        apiService = ApiService.create()
    }

    fun loadWeatherNow() {
        apiService.getAllWeather()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            try {
                                if (!it.error)
                                    view.onDataLoaded(it.data
                                            .filter { DateUtils.isToday(it.date.changeDateToLong()) }
                                            .first())
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        },
                        {
                            it.printStackTrace()
                        }
                )
    }
}