package com.karrel.openweather.repository

/**
 * Created by Rell on 2019. 4. 18..
 */
interface WeatherDataSource {
    fun loadWeatherListData(onSuccess: () -> Unit, onFailure: (t: Throwable) -> Unit)
    fun loadCurrentCityData(cityId: Int, onSuccess: () -> Unit, onFailure: (t: Throwable) -> Unit)
    fun loadHourlyForecastData(cityId: Int, onSuccess: () -> Unit, onFailure: (t: Throwable) -> Unit)
    fun load3HourlyForecastData(cityId: Int, onSuccess: () -> Unit, onFailure: (t: Throwable) -> Unit)
    fun getGoogleImageUrl(cityId: Int, size: Pair<Int, Int>): String?
}