package com.solusi247.weather247.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.solusi247.weather247.R
import com.solusi247.weather247.fragment.WeatherFragment
import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.module.presenter.DetailPresenter
import com.solusi247.weather247.module.view.DetailView
import com.solusi247.weather247.utils.Constant
import com.solusi247.weather247.utils.changeFormatDate
import com.solusi247.weather247.utils.endCustomLoading
import com.solusi247.weather247.utils.startCustomLoading
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.no_connection.*
import kotlinx.android.synthetic.main.progress_loading.*

class DetailActivity : AppCompatActivity(), DetailView {

    lateinit var presenter: DetailPresenter

    lateinit var day: String
    lateinit var date: String
    lateinit var textDate: String

    /************************************************************************************/
    /*********************   Override Function AppCompatActivity   **********************/
    /************************************************************************************/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Initialize activity presenter
        presenter = DetailPresenter(this)

        setSupportActionBar(toolbar)

        day = intent.getStringExtra(Constant.SHARED_DAY)
        date = intent.getStringExtra(Constant.SHARED_DATE)

        textDate = "$day, ${date.changeFormatDate()}"
        tvDate.text = textDate

        noConnection.setOnClickListener { _ ->
            presenter.loadDetailWeather(date)
            noConnection.visibility = View.GONE
        }

        // Presenter load detail weather
        presenter.loadDetailWeather(date)
    }

    override fun onDestroy() {
        presenter.interruptService()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    /**********************End of Override Function AppCompatActivity*********************/


    /*************************************************************************************/
    /**************************************   View   *************************************/
    /*************************************************************************************/

    override fun showLoading() = ivLoading.startCustomLoading()

    override fun hideLoading() = ivLoading.endCustomLoading()

    override fun showError() {
        noConnection.visibility = View.VISIBLE
    }

    override fun onWeatherLoaded(dataDetailWeathers: List<ResponseModel.DataDetailWeather>) {
        val weatherFragment = WeatherFragment().apply {
            this.dataDetailWeathers = dataDetailWeathers
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, weatherFragment)
                .commit()
    }
    /***************************************End of View**********************************/

}