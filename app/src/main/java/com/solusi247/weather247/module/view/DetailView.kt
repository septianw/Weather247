package com.solusi247.weather247.module.view

import com.solusi247.weather247.module.model.ResponseModel

interface DetailView {

    fun showLoading()

    fun hideLoading()

    fun onListWeather(dataDetailWeathers: List<ResponseModel.DataDetailWeather>)
}