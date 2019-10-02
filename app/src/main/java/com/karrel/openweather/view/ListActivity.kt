package com.karrel.openweather.view

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.karrel.openweather.R
import com.karrel.openweather.base.BaseActivity
import com.karrel.openweather.view.adapter.CurrentListAdapter
import com.karrel.openweather.viewmodel.CurrentViewModel
import kotlinx.android.synthetic.main.activity_weather_list.*
import kotlinx.android.synthetic.main.content_weather_list.*

class ListActivity : BaseActivity() {

    private val listAdapter: CurrentListAdapter by lazy {
        CurrentListAdapter { cityId, cityName ->
            DetailActivity.startActivity(this, cityId, cityName)
        }
    }

    private val viewModel by lazy { CurrentViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_list)
        setSupportActionBar(toolbar)

        setupRecyclerView()
        observeData()

        startIntro()
        viewModel.loadWeatherListData()
        setupSwipeRefresh()
    }

    private fun setupSwipeRefresh() {
        swiperRefresh.setColorSchemeResources(
            R.color.colorPrimary,
            R.color.colorAccent
        )

        swiperRefresh.setOnRefreshListener {
            viewModel.loadWeatherListData()
        }
    }

    private fun startIntro() {
        startActivity(Intent(this, IntroActivity::class.java))
        overridePendingTransition(0, 0)
    }

    private fun observeData() {
        viewModel.apply {
            currentList.observe(this@ListActivity, Observer {
                listAdapter.setData(it)
            })
        }
    }

    private fun setupRecyclerView() {
        recyclerWeather.apply {
            adapter = listAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun showProgressDialog() {
//        imageProgress.visibility = View.VISIBLE
    }

    override fun hideProgressDialog() {
        swiperRefresh.isRefreshing = false
    }
}
