package com.solusi247.weather247.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.solusi247.weather247.R
import com.solusi247.weather247.adapter.DetailAdapter
import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.module.presenter.DetailPresenter
import com.solusi247.weather247.module.view.DetailView
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        /********************************Set layout************************************/
        supportActionBar?.elevation = 1f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val layoutManager = LinearLayoutManager(this)
        rvWeathers.layoutManager = layoutManager
        /****************************End of set layout**********************************/


        val date = intent.getStringExtra("date")

        // Declare activity presenter
        val presenter = DetailPresenter(this)

        // Presenter load detail weather
        presenter.loadDetailWeather(date)
    }

    /***************************************************************************************/
    /**************************************   View   ***************************************/
    /***************************************************************************************/
    override fun showLoading() {
        pbLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbLoading.visibility = View.INVISIBLE
    }

    override fun onListWeather(dataDetailWeathers: List<ResponseModel.DataDetailWeather>) {
        rvWeathers.adapter = DetailAdapter(this, dataDetailWeathers)
    }
    /***************************************End of View************************************/


    /***************************************************************************************/
    /*********************   Override Function App Compact Activity   **********************/
    /***************************************************************************************/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
    /**********************End of Override Function App Compact Activity*********************/
}