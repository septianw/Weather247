package com.solusi247.weather247.view

import com.solusi247.weather247.model.ResponseModel

/**
 * Created by aldidwikip on 11/01/2018.
 */
interface DetailView {

    fun onListWeather(dataDetailWeathers: List<ResponseModel.DataDetailWeather>)
}