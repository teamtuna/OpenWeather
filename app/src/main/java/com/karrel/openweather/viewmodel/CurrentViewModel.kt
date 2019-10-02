package com.karrel.openweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.karrel.openweather.base.BaseViewModel
import com.karrel.openweather.extension.KtoC
import com.karrel.openweather.extension.toAmPm
import com.karrel.openweather.extension.toLottieIcon
import com.karrel.openweather.model.weather.CurrentWeather
import com.karrel.openweather.repository.WeatherRepository

/**
 * 현재 날씨 정보에대한 뷰모델
 */
class CurrentViewModel : BaseViewModel() {
    private val weatherRepo = WeatherRepository

    val currentList: LiveData<List<CurrentWeather>> = Transformations.map(weatherRepo.currentWeatherData) { data ->
        data.list
    }

    val lottieIcon: LiveData<String> = Transformations.map(weatherRepo.currentCityData) {
        it.weather[0].icon.toLottieIcon()
    }

    val checkedTime: LiveData<String> = Transformations.map(weatherRepo.currentCityData) {
        it.dt.toAmPm()
    }

    val currentTemp: LiveData<String> = Transformations.map(weatherRepo.currentCityData) {
        it.main.temp.KtoC()
    }
    val currentTempHigh: LiveData<String> = Transformations.map(weatherRepo.currentCityData) {
        it.main.temp_max.KtoC()
    }
    val currentTempLow: LiveData<String> = Transformations.map(weatherRepo.currentCityData) {
        it.main.temp_min.KtoC()
    }


    fun loadWeatherListData() {
        showProgress()
        weatherRepo.loadWeatherListData({
            hideProgress()
        }, {
            hideProgress()
            throwMessage(it)
        })
    }

    fun loadCurrentCityData(cityId: Int) {
        showProgress()
        weatherRepo.currentWeatherData.value?.list?.let {
            for (item in it) {
                if (item.id == cityId) {
                    weatherRepo.currentCityData.postValue(item)
                }
            }
        }

        weatherRepo.loadCurrentCityData(cityId, {
            hideProgress()
        }, {
            hideProgress()
            throwMessage(it)
        })
    }

    fun getGooglemapImageUrl(cityId: Int, size: Pair<Int, Int>): String? {
        return weatherRepo.getGoogleImageUrl(cityId, size)
    }

}