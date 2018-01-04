package com.solusi247.weather247

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity(), LastWeatherListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.elevation = 1f

        val clWeatherNow = findViewById<ConstraintLayout>(R.id.cl_weather_now)
        val rvLastWeather = findViewById<RecyclerView>(R.id.rv_last_weather)

        rvLastWeather.adapter = MainAdapter(this, this)
        val gridLayoutManager = GridLayoutManager(this, 3)
        rvLastWeather.layoutManager = gridLayoutManager

        clWeatherNow.setOnClickListener { goToDetail() }
    }

    override fun goToDetail() {
        startActivity(Intent(this, DetailActivity::class.java))
    }

}
