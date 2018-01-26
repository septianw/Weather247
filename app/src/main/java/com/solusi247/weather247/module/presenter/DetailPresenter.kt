package com.solusi247.weather247.module.presenter

import com.solusi247.weather247.Weather247
import com.solusi247.weather247.module.view.DetailView
import com.solusi247.weather247.service.ApiService
import com.solusi247.weather247.utils.Constant
import com.solusi247.weather247.utils.Message
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailPresenter(val view: DetailView) {

    val apiService: ApiService

    init {
        apiService = ApiService.create()
    }

    fun loadDetailWeather(date: String) {
        view.showLoading()
        apiService.getWeatherDetails(date)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { result ->
                            try {
                                if (!result.error) {
                                    //Result successfull
                                    view.onWeatherLoaded(result.data)
                                } else {
                                    // Connection success but error in result
                                    view.showError()
                                    Message.showToast(Weather247.context, result.message, Message.ERROR)
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                                view.showError()
                                Message.showToast(Weather247.context, Constant.RESULT_ERROR, Message.ERROR)
                            } finally {
                                view.hideLoading()
                            }
                        },
                        { error ->
                            error.printStackTrace()
                            view.showError()
                            Message.showToast(Weather247.context, Constant.PROBLEM_SERVER, Message.ERROR)
                            view.hideLoading()
                        }
                )
    }
}