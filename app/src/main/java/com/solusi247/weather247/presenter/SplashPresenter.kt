package com.solusi247.weather247.presenter

import com.solusi247.weather247.listener.SplashListener

class SplashPresenter(val listener: SplashListener) {

    fun loadSplash() {
        val duration = 2000L
        listener.showSplashScreen(duration)
    }

}
