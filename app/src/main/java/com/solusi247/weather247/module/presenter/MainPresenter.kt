package com.solusi247.weather247.module.presenter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.Log
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.solusi247.weather247.R
import com.solusi247.weather247.Weather247
import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.module.view.MainView
import com.solusi247.weather247.service.ApiService
import com.solusi247.weather247.utils.Constant
import com.solusi247.weather247.utils.Message
import com.solusi247.weather247.utils.MqttHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.json.JSONObject


class MainPresenter(val view: MainView) {

    lateinit var subscription: Disposable
    lateinit var dataWeather: ResponseModel.DataWeather
    lateinit var listDataWeather: List<ResponseModel.DataWeather>

    val apiService: ApiService
    val context: Context
    val mqttHelper: MqttHelper

    init {
        apiService = ApiService.create()
        context = Weather247.context
        mqttHelper = MqttHelper(context)
    }

    fun interruptService() = subscription.dispose()

    fun initGraph(lineChart: LineChart, type: String) {

        mqttHelper.setCallBack(object : MqttCallbackExtended {
            override fun connectComplete(reconnect: Boolean, serverURI: String?) {
            }

            override fun connectionLost(cause: Throwable) {
                cause.printStackTrace()
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                val lineData = lineChart.data

                var set = lineData.getDataSetByIndex(0)
                if (set == null) {
                    set = LineDataSet(null, null).apply {
                        color = ContextCompat.getColor(context, R.color.colorGraph)
                        valueTextSize = 12f
                        setDrawFilled(true)
                        fillAlpha = 50
                        lineWidth = 3f
                        fillColor = ContextCompat.getColor(context, R.color.colorGraph)
                        mode = LineDataSet.Mode.HORIZONTAL_BEZIER
                        setCircleColor(ContextCompat.getColor(context, R.color.colorSecondary))
                        circleRadius = 5f
                        isHighlightEnabled = false
                    }
                    lineData.addDataSet(set)
                }

                val jsonObject = JSONObject(message.toString())
                val data = jsonObject.getString(type)
                view.onMQTTUpdated(Entry(set.entryCount.toFloat(), data.toFloat()))
            }
        })

    }

    fun loadWeather() {
        view.showLoading()
        subscription = apiService.getWeatherNow()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            try {
                                if (!it.error) {
                                    // Result successfull
                                    dataWeather = it.data.first()
                                    Log.i("dataWeather", dataWeather.toString())

                                    loadWeatherHistory()
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
                        }
                )

    }

    fun loadWeatherHistory() {
        view.showLoading()
        subscription = apiService.getAllWeather()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            try {
                                if (!it.error) {
                                    // Result successfull
                                    view.playAnimationWeatherToday()
                                    view.onWeatherToday(dataWeather)
                                    view.onLastWeather(it.data)

                                    listDataWeather = it.data
                                    Log.i("dataWeather", dataWeather.toString())
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
        apiService.getWeatherNow()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            try {
                                if (!it.error) {
                                    view.playAnimationWeatherToday()
                                    // Result successfull
                                    view.onWeatherToday(it.data.first())
                                    view.onLastWeather(listDataWeather)

                                    dataWeather = it.data.first()
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