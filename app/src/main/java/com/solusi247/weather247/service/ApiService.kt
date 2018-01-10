package com.solusi247.weather247.service

import com.solusi247.weather247.model.ResponseModel
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("api/weather")
    fun getAllWeather(): Observable<ResponseModel.Weather>

    @GET("api/weather/weather_details/{date}")
    fun getWeatherDetails(@Path("date") date: String): Observable<ResponseModel.DetailWeather>

    companion object Factory {

        val BASE_URL = "https://weather247.000webhostapp.com/"

        fun create(): ApiService {
            val httpClient = OkHttpClient().newBuilder().addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Accept", "application/json").build()
                return@addInterceptor chain.proceed(request)
            }.build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .client(httpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}