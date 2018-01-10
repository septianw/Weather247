package com.solusi247.weather247.presenter

import com.solusi247.weather247.Weather247
import com.solusi247.weather247.model.ResponseModel
import com.solusi247.weather247.service.ApiService
import com.solusi247.weather247.utils.Constant
import com.solusi247.weather247.utils.Message
import com.solusi247.weather247.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(val view: MainView) {

    var apiService: ApiService

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
                                    Message.showToast(Weather247.context, result.message)
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                                Message.showToast(Weather247.context, Constant.RESULT_ERROR)
                            }
                        },
                        { error ->
                            error.printStackTrace()
                            Message.showToast(Weather247.context, Constant.PROBLEM_SERVER)
                        }
                )
    }

    fun addUnits(dataWeather: ResponseModel.DataWeather) {
        dataWeather.temperature = "${dataWeather.temperature}\u2103"
        dataWeather.pressure = "${dataWeather.pressure} hPa"
        dataWeather.humidity = "${dataWeather.humidity}%"
    }
}