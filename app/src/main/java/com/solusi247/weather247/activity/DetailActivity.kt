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

    lateinit var date: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.move_up_in_500, R.anim.move_up_out_500)
        setContentView(R.layout.activity_detail)

        /********************************Set layout************************************/
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        date = intent.getStringExtra(Constant.SHARED_DATE)

        toolbar.title = getString(R.string.weather_details)
        tvDate.text = date.changeFormatDate()
        /****************************End of set layout**********************************/


        // Init activity presenter
        presenter = DetailPresenter(this)

        // Presenter load detail weather
        presenter.loadDetailWeather(date)
    }

    /***************************************************************************************/
    /**************************************   View   ***************************************/
    /***************************************************************************************/

    override fun showLoading() {
        ivLoading.startCustomLoading()
    }

    override fun hideLoading() {
        ivLoading.endCustomLoading()
    }

    override fun showError() {
        noConnection.visibility = View.VISIBLE
        noConnection.setOnClickListener { _ ->
            presenter.loadDetailWeather(date)
            noConnection.visibility = View.GONE
        }
    }

    override fun onWeatherLoaded(dataDetailWeathers: List<ResponseModel.DataDetailWeather>) {
        val weatherFragment = WeatherFragment()
        weatherFragment.dataDetailWeathers = dataDetailWeathers
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, weatherFragment)
                .commit()
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