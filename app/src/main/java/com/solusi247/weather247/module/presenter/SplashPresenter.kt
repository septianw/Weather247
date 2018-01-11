package com.solusi247.weather247.module.presenter

import com.solusi247.weather247.module.view.SplashView

class SplashPresenter(val view: SplashView) {

    fun loadSplash() {
        val duration = 1000L
        view.showSplashScreen(duration)
    }

}
