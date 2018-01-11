package com.solusi247.weather247.presenter

import com.solusi247.weather247.Weather247
import com.solusi247.weather247.service.ApiService
import com.solusi247.weather247.utils.Constant
import com.solusi247.weather247.utils.Message
import com.solusi247.weather247.view.DetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by aldidwikip on 11/01/2018.
 */
class DetailPresenter(val view: DetailView) {

    var apiService: ApiService

    init {
        apiService = ApiService.create()
    }

    fun loadDetailWeather(date: String) {
        apiService.getWeatherDetails(date + "")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { result ->
                            try {
                                if (!result.error) {
                                    view.onListWeather(result.data)
                                } else {
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
}