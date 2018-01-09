package com.solusi247.weather247

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val rvWeathers = findViewById<RecyclerView>(R.id.rvWeathers)
        val pbLoading = findViewById<ProgressBar>(R.id.pb_loading_weather)

        supportActionBar?.elevation = 1f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val layoutManager = LinearLayoutManager(this)
        rvWeathers.layoutManager = layoutManager
        rvWeathers.adapter = DetailAdapter(this)

        pbLoading.visibility = View.INVISIBLE
    }

}
