package com.karrel.openweather.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.karrel.openweather.model.FindCurrentWeatherData
import com.karrel.openweather.model.weather.CurrentWeather
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * Created by Rell on 2019. 4. 19..
 */
class CurrentViewModelTest : LifecycleOwner {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    private val lifecycle = LifecycleRegistry(this)

    override fun getLifecycle() = lifecycle

    private val viewModel: CurrentViewModel by lazy { CurrentViewModel() }


    @Before
    fun initViewModel() {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        viewModel.loadWeatherListData()
        Thread.sleep(1000L)
    }

    @Test
    fun currentList() {
        var data: List<CurrentWeather>? = null
        viewModel.currentList.observe(this, Observer {
            data = it
        })

        viewModel.loadCurrentCityData(1897000)
        Thread.sleep(1000L)

        data?.let {
            println("currentList : $it")
            assert(true)
        } ?: assert(false)
    }

    @Test
    fun lottieIcon() {
        var data: String? = null
        viewModel.lottieIcon.observe(this, Observer {
            data = it
        })

        viewModel.loadCurrentCityData(1897000)
        Thread.sleep(1000L)
        data?.let {
            println("lottieIcon : $it")
            assert(true)
        } ?: assert(false)
    }

    @Test
    fun checkedTime() {
        var data: String? = null
        viewModel.checkedTime.observe(this, Observer {
            data = it
        })

        viewModel.loadCurrentCityData(1897000)
        Thread.sleep(1000L)

        println("checkedTime : $data")
        data?.let {
            println("checkedTime : $it")
            assert(true)
        } ?: assert(false)
    }

    @Test
    fun currentTemp() {
        var data: String? = null
        viewModel.currentTemp.observe(this, Observer {
            data = it
        })

        viewModel.loadCurrentCityData(1897000)
        Thread.sleep(1000L)

        data?.let {
            println("currentTemp : $it")
            assert(true)
        } ?: assert(false)
    }

    @Test
    fun currentTempHigh() {
        var data: String? = null
        viewModel.currentTempHigh.observe(this, Observer {
            data = it
        })

        viewModel.loadCurrentCityData(1897000)
        Thread.sleep(1000L)

        data?.let {
            println("currentTempHigh : $it")
            assert(true)
        } ?: assert(false)
    }

    @Test
    fun currentTempLow() {
        var data: String? = null
        viewModel.currentTempLow.observe(this, Observer {
            data = it
        })

        viewModel.loadCurrentCityData(1897000)
        Thread.sleep(1000L)

        data?.let {
            println("currentTempLow : $it")
            assert(true)
        } ?: assert(false)
    }


    @Test
    fun getGooglemapImageUrl() {
        viewModel.loadWeatherListData()
        Thread.sleep(1000L)

        val cityId = 1897000
        val size = Pair(1000, 1000)
        val url = viewModel.getGooglemapImageUrl(cityId, size)

        assert(url == "https://maps.googleapis.com/maps/api/staticmap?zoom=13&size=1000x1000&maptype=roadmap&markers=color:red%7Clabel:C%7C37.4386,127.1378&key=AIzaSyDP9laAKtbDd22DM9eq9NBum9wWzt-90es")

        println("url : $url")
    }
}