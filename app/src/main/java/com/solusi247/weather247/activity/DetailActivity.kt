package com.solusi247.weather247.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.solusi247.weather247.R
import com.solusi247.weather247.adapter.DetailAdapter
import com.solusi247.weather247.module.model.ResponseModel
import com.solusi247.weather247.module.presenter.DetailPresenter
import com.solusi247.weather247.module.view.DetailView
import com.solusi247.weather247.utils.Message
import com.solusi247.weather247.utils.changeFormatDate
import com.solusi247.weather247.utils.endCustomLoading
import com.solusi247.weather247.utils.startCustomLoading
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailView {

    lateinit var zoomIn: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.move_up_in_500, R.anim.move_up_out_500)
        setContentView(R.layout.activity_detail)

        zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in)

        /********************************Set layout************************************/
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        val date = intent.getStringExtra("date")

        toolbar.title = "Weather Details"
        tvDate.text = date.changeFormatDate()

        val layoutManager = LinearLayoutManager(this)
        val dividerItem = DividerItemDecoration(rvWeathers.context, layoutManager.orientation)
        dividerItem.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_item)!!)
        rvWeathers.layoutManager = layoutManager
        rvWeathers.addItemDecoration(dividerItem)
        /****************************End of set layout**********************************/


        // Declare activity presenter
        val presenter = DetailPresenter(this)

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

    override fun onListWeather(dataDetailWeathers: List<ResponseModel.DataDetailWeather>) {
        rvWeathers.adapter = DetailAdapter(this, dataDetailWeathers, rvWeathers)
    }
    /***************************************End of View************************************/


    /***************************************************************************************/
    /*********************   Override Function App Compact Activity   **********************/
    /***************************************************************************************/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.actionGraphic -> Message.showToast(this, "Menu graph pressed", Message.INFORMATION)
        }
        return super.onOptionsItemSelected(item)
    }
    /**********************End of Override Function App Compact Activity*********************/
}