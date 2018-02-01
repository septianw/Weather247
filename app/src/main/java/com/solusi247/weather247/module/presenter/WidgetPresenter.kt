package com.solusi247.weather247.module.presenter

import com.solusi247.weather247.module.view.WidgetView
import com.solusi247.weather247.service.ApiService
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
                                    view.onDataLoaded(it.data[0])
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