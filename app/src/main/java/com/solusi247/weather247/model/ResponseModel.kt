package com.solusi247.weather247.model

data class ResponseModel(
        val error: Boolean,
        val message: String
)

data class WeatherResponseModel(
        val error: Boolean,
        val message: String,
        val data: List<DataDetailWeather>
)

data class DetailResponseModel(
        val error: Boolean,
        val message: String,
        val data: List<DataWeather>
)

data class DataWeather(
        val id: Int,
        val weather: String,
        val day: String,
        val date: String,
        val temperature: String,
        val pressure: String,
        val humidity: String
)

data class DataDetailWeather(
        val id: Int,
        val weather: String,
        val day: String,
        val date: String,
        val temperature: String,
        val pressure: String,
        val humidity: String
)