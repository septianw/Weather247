package com.solusi247.weather247.module.view

import com.solusi247.weather247.module.model.ResponseModel

/**
 * Created by aldidwikip on 25/01/2018.
 */
interface GraphView {

    fun onGraphWeather(dataDetailWeathers: List<ResponseModel.DataDetailWeather>)
}