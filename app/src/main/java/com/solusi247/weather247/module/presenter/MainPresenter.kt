package com.solusi247.weather247.module.presenter

import android.content.Context
import com.solusi247.weather247.Weather247
import com.solusi247.weather247.module.view.MainView
import com.solusi247.weather247.service.ApiService
import com.solusi247.weather247.utils.Constant
import com.solusi247.weather247.utils.Message
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(val view: MainView) {

    lateinit var subscription: Disposable

    val apiService: ApiService
    val context: Context

    init {
        apiService = ApiService.create()
        context = Weather247.context
    }

    fun interruptService() = subscription.dispose()

    fun loadWeather() {
        view.showLoading()
        subscription = apiService.getAllWeather()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            try {
                                if (!it.error) {
                                    view.playAnimationWeatherToday()
                                    // Result successfull
                                    view.onWeatherToday(it.data[0])

                                    view.onLastWeather(it.data)
                                } else {
                                    // Connection success but error in result
                                    view.showError()
                                    Message.showToast(context, it.message, Message.Type.ERROR)
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                                view.showError()
                                Message.showToast(context, Constant.RESULT_ERROR, Message.Type.ERROR)
                            } finally {
                                view.hideLoading()
                            }
                        },
                        {
                            it.printStackTrace()
                            view.showError()
                            Message.showToast(context, Constant.PROBLEM_SERVER, Message.Type.ERROR)
                            view.hideLoading()
                        }
                )
    }

    fun refreshWeather() {
        view.startRefresh()
        apiService.getAllWeather()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            try {
                                if (!it.error) {
                                    view.playAnimationWeatherToday()
                                    // Result successfull
                                    view.onWeatherToday(it.data[0])

                                    view.onLastWeather(it.data)
                                } else {
                                    // Connection success but error in result
                                    Message.showToast(context, it.message, Message.Type.ERROR)
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                                Message.showToast(context, Constant.RESULT_ERROR, Message.Type.ERROR)
                            } finally {
                                view.stopRefresh()
                            }
                        },
                        {
                            it.printStackTrace()
                            Message.showToast(context, Constant.PROBLEM_SERVER, Message.Type.ERROR)
                            view.stopRefresh()
                        }
                )
    }

}