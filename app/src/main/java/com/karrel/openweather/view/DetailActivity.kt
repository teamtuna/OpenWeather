package com.karrel.openweather.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.karrel.openweather.R
import com.karrel.openweather.base.BaseActivity
import com.karrel.openweather.extension.alphaAnim
import com.karrel.openweather.extension.loadImage
import com.karrel.openweather.extension.plusAssign
import com.karrel.openweather.view.adapter.Forecast3HourlyAdapter
import com.karrel.openweather.view.adapter.ForecastHourlyAdapter
import com.karrel.openweather.viewmodel.CurrentViewModel
import com.karrel.openweather.viewmodel.ForecastViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_weather_detail.*
import kotlinx.android.synthetic.main.content_detail_current.*
import kotlinx.android.synthetic.main.content_weather_detail.*
import java.util.concurrent.TimeUnit


class DetailActivity : BaseActivity() {

    private var cityId: Int = -1
    private val currentViewModel by lazy { CurrentViewModel() }
    private val forecastViewModel by lazy { ForecastViewModel() }

    private val hourlyAdapter by lazy { ForecastHourlyAdapter() }
    private val threeHourlyAdapter by lazy { Forecast3HourlyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)

        setupActionBar()
        parseExtra()
        setupRecyclerView()
        observeDatas()
        imageMap.doOnPreDraw { setupMapImage() }

    }


    override fun onStart() {
        super.onStart()

        loadDatas()
    }

    private fun loadDatas() {
        currentViewModel.loadCurrentCityData(cityId)
        forecastViewModel.load3HourlyForecastData(cityId)
    }

    private fun observeDatas() {
        currentViewModel.apply {
            lottieIcon.observe(this@DetailActivity, Observer {
                imageIcon.setAnimation(it)
                imageIcon.playAnimation()
            })

            checkedTime.observe(this@DetailActivity, Observer {
                textCurrentTime.text = it
            })

            currentTemp.observe(this@DetailActivity, Observer { textCurrentTemp.text = it })
            currentTempHigh.observe(this@DetailActivity, Observer { textHighTemp.text = it })
            currentTempLow.observe(this@DetailActivity, Observer { textLowTemp.text = it })
        }

        forecastViewModel.apply {
            hourlyForecastList.observe(this@DetailActivity, Observer {
                it?.let { it1 -> hourlyAdapter.setData(it1) }
            })

            threeHourlyList.observe(this@DetailActivity, Observer { data ->
                // 시간별 데이터보다 먼저 update시 애니메이션이 이상해서 200ms 딜레이
                if (data != null) {
                    disposables +=
                        Observable.timer(200L, TimeUnit.MILLISECONDS)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe { threeHourlyAdapter.setData(data) }
                }
            })
        }
    }

    private fun setupMapImage() {
        val size = Pair(imageMap.width, imageMap.height)
        currentViewModel.getGooglemapImageUrl(cityId, size)
            .also {
                it?.let { it1 -> imageMap.loadImage(it1, onSuccess = { imageMap.alphaAnim() }) }
            }
    }

    private fun parseExtra() {
        cityId = intent.getIntExtra(EXTRA_CITY_ID, -1)
        if (cityId == -1) throw IllegalStateException("cityId does not exist.")

        val title = intent.getStringExtra(EXTRA_CITY_NAME)
        setTitle(title)
    }

    private fun setupRecyclerView() {
        recyclerHourly.apply {
            adapter = hourlyAdapter
            layoutManager = GridLayoutManager(this@DetailActivity, 6)
        }

        recyclerDaily.apply {
            adapter = threeHourlyAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private const val EXTRA_CITY_ID = "cityId"
        private const val EXTRA_CITY_NAME = "cityName"

        fun startActivity(context: Context, cityId: Int, cityName: String = "") {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_CITY_ID, cityId)
            intent.putExtra(EXTRA_CITY_NAME, cityName)
            context.startActivity(intent)
        }
    }
}
