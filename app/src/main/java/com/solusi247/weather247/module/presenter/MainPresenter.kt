package com.solusi247.weather247.module.presenter

import com.solusi247.weather247.Weather247
import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.module.view.MainView
import com.solusi247.weather247.service.ApiService
import com.solusi247.weather247.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(val view: MainView) {

    val apiService: ApiService

    init {
        apiService = ApiService.create()
    }

    fun loadWeather() {
        apiService.getAllWeather()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { result ->
                            try {
                                if (!result.error) {
                                    // Result successfull
                                    addUnits(result.data[0])
                                    view.onWeatherToday(result.data[0])

                                    view.onLastWeather(result.data)
                                } else {
                                    // Connection success but error in result
                                    Message.showToast(Weather247.context, result.message, Message.ERROR)
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                                Message.showToast(Weather247.context, Constant.RESULT_ERROR, Message.ERROR)
                            }
                        },
                        { error ->
                            error.printStackTrace()
                            Message.showToast(Weather247.context, Constant.PROBLEM_SERVER, Message.ERROR)
                        }
                )
    }

    fun addUnits(dataWeather: ResponseModel.DataWeather) {
        dataWeather.temperature = dataWeather.temperature.addUnitTemperature()
        dataWeather.pressure = dataWeather.pressure.addUnitPressure()
        dataWeather.humidity = dataWeather.humidity.addUnitHumidity()
    }
}