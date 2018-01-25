package com.solusi247.weather247.module.presenter

import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.module.view.GraphView

/**
 * Created by aldidwikip on 25/01/2018.
 */
class GraphPresenter(val view: GraphView) {

    fun initGraphDetails(dataDetailWeathers: List<ResponseModel.DataDetailWeather>) {
        view.onGraphWeather(dataDetailWeathers)
    }
}