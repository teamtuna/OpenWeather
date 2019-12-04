package com.karrel.openweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.karrel.openweather.base.BaseViewModel
import team.tuna.openweather.model.weather.CurrentWeather
import team.tuna.openweather.repository.WeatherRepository

/**
 * Created by Rell on 2019. 4. 19..
 */
class ForecastViewModel : BaseViewModel() {
    private val weatherRepo = WeatherRepository
    private var hourlyCityId: Int = -1
    private var _5day3hourlyCityId: Int = -1

    val hourlyForecastList: LiveData<List<CurrentWeather>?> = Transformations.map(weatherRepo.hourlyForecastData) {
        if (it.city?.id != hourlyCityId) {
            return@map null
        }
        it.list
    }

    val threeHourlyList: LiveData<List<CurrentWeather>?> = Transformations.map(weatherRepo.forecast3HourlyData) {
        if (it.city?.id != _5day3hourlyCityId) {
            return@map null
        }
        it.list
    }


    fun loadHourlyForecastData(cityId: Int) {
        hourlyCityId = cityId
        showProgress()

        weatherRepo.loadHourlyForecastData(cityId,
            { hideProgress() },
            {
                hideProgress()
                throwMessage(it)
            })
    }

    fun load3HourlyForecastData(cityId: Int) {
        _5day3hourlyCityId = cityId
        showProgress()

        weatherRepo.load3HourlyForecastData(cityId,
            { hideProgress() },
            {
                hideProgress()
                throwMessage(it)
            })
    }

}