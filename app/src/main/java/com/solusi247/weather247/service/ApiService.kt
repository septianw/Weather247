package com.solusi247.weather247.service

import com.solusi247.weather247.module.model.ResponseModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("api/weather.php")
    fun getAllWeather(): Observable<ResponseModel.Weather>

    @GET("api/weather_detail.php")
    fun getWeatherNow(): Observable<ResponseModel.Weather>

    @GET("api/weather_detail.php")
    fun getWeatherDetails(@Query("date") date: String,
                          @Query("duration") duration: Int)
            : Observable<ResponseModel.DetailWeather>

    companion object {

        val BASE_URL = "http://192.168.0.118:80/weather247/"

        fun create(): ApiService {

            val retrofit = Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}