package com.solusi247.weather247.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.solusi247.weather247.R
import com.solusi247.weather247.adapter.DetailAdapter
import com.solusi247.weather247.model.ResponseModel
import com.solusi247.weather247.presenter.DetailPresenter
import com.solusi247.weather247.view.DetailView
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by aldidwikip on 11/01/2018.
 */
class DetailActivity : AppCompatActivity(), DetailView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.elevation = 1f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val layoutManager = LinearLayoutManager(this)
        rvWeathers.layoutManager = layoutManager

        pbLoading.visibility = View.INVISIBLE

        val intentObject: Intent = intent
        val date = intentObject.getStringExtra("date")

        val presenter = DetailPresenter(this)
        presenter.loadDetailWeather(date)
    }

    override fun onListWeather(dataDetailWeathers: List<ResponseModel.DataDetailWeather>) {
        rvWeathers.adapter = DetailAdapter(this, dataDetailWeathers)
    }
}