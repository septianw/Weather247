package com.solusi247.weather247

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context


class Weather247 : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}