package com.solusi247.weather247.presenter

import com.solusi247.weather247.view.SplashView

class SplashPresenter(val view: SplashView) {

    fun loadSplash() {
        val duration = 1500L
        view.showSplashScreen(duration)
    }

}
