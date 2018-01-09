package com.solusi247.weather247.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.solusi247.weather247.MainActivity
import com.solusi247.weather247.R
import com.solusi247.weather247.listener.SplashListener
import com.solusi247.weather247.presenter.SplashPresenter

class SplashActivity : AppCompatActivity(), SplashListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val presenter = SplashPresenter(this)

        // Used to hide status bar
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
        decorView.systemUiVisibility = uiOptions

        presenter.loadSplash()
    }

    /**************************************View*******************************************/
    override fun showSplashScreen(duration: Long) {
        Handler().postDelayed({
            val intentToMain = Intent(this, MainActivity::class.java)
            startActivity(intentToMain)
            finish()
        }, duration)
    }
    /***********************************End of View****************************************/
}
