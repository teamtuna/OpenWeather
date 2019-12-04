package com.karrel.openweather.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import team.tuna.openweather.model.weather.CurrentWeather
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ForecastViewModelTest : LifecycleOwner {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    private val lifecycle = LifecycleRegistry(this)

    override fun getLifecycle() = lifecycle

    private val viewmodel: ForecastViewModel by lazy { ForecastViewModel() }


    @Before
    fun initViewModel() {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    @Test
    fun loadHourlyForecastData() {
        var data: List<CurrentWeather>? = null
        viewmodel.hourlyForecastList.observe(this, Observer {
            data = it
        })

        viewmodel.loadHourlyForecastData(1897000)
        Thread.sleep(1000L)
        println("loadHourlyForecastData : $data")
        data?.let {
            println("loadHourlyForecastData : $it")
            assert(true)
        } ?: assert(false)
    }

    @Test
    fun load3HourlyForecastData() {
        var data: List<CurrentWeather>? = null
        viewmodel.threeHourlyList.observe(this, Observer {
            data = it
        })

        viewmodel.loadHourlyForecastData(1897000)
        Thread.sleep(1000L)
        data?.let {
            println("load3HourlyForecastData : $it")
            assert(true)
        } ?: assert(false)
    }

}