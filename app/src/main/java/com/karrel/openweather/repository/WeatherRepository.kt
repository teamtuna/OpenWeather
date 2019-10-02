package com.karrel.openweather.repository

import androidx.lifecycle.MutableLiveData
import com.karrel.openweather.model.FindCurrentWeatherData
import com.karrel.openweather.model.Forecast5day3hourData
import com.karrel.openweather.model.HourlyForecastData
import com.karrel.openweather.model.weather.CurrentWeather
import com.karrel.openweather.network.WeatherRetrofit
import com.karrel.openweather.network.googlemapImageUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object WeatherRepository : WeatherDataSource {
    val currentWeatherData: MutableLiveData<FindCurrentWeatherData> by lazy {
        MutableLiveData<FindCurrentWeatherData>()
    }

    val currentCityData: MutableLiveData<CurrentWeather> by lazy {
        MutableLiveData<CurrentWeather>()
    }

    val hourlyForecastData: MutableLiveData<HourlyForecastData> by lazy {
        MutableLiveData<HourlyForecastData>()
    }

    val forecast3HourlyData: MutableLiveData<Forecast5day3hourData> by lazy {
        MutableLiveData<Forecast5day3hourData>()
    }

    override fun loadHourlyForecastData(
        cityId: Int,
        onSuccess: () -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {

        WeatherRetrofit.hourlyForecast(cityId).enqueue(object :
            Callback<HourlyForecastData> {
            override fun onFailure(call: Call<HourlyForecastData>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(call: Call<HourlyForecastData>, response: Response<HourlyForecastData>) {
                onSuccess()
                hourlyForecastData.postValue(response.body())
            }

        })
    }

    override fun loadWeatherListData(
        onSuccess: () -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {

        WeatherRetrofit.findCurrentWeatherData().enqueue(object :
            Callback<FindCurrentWeatherData> {
            override fun onFailure(call: Call<FindCurrentWeatherData>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(call: Call<FindCurrentWeatherData>, response: Response<FindCurrentWeatherData>) {
                onSuccess()
                currentWeatherData.postValue(response.body())
                println("currentWeatherData : $currentWeatherData")
            }

        })
    }

    override fun loadCurrentCityData(
        cityId: Int,
        onSuccess: () -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {

        WeatherRetrofit.currentWeatherData(cityId).enqueue(object : Callback<CurrentWeather> {
            override fun onFailure(call: Call<CurrentWeather>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(call: Call<CurrentWeather>, response: Response<CurrentWeather>) {
                onSuccess()
                currentCityData.postValue(response.body())
            }

        })
    }

    override fun load3HourlyForecastData(cityId: Int, onSuccess: () -> Unit, onFailure: (t: Throwable) -> Unit) {
        WeatherRetrofit.forecast5day3hour(cityId).enqueue(object : Callback<Forecast5day3hourData> {
            override fun onFailure(call: Call<Forecast5day3hourData>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(call: Call<Forecast5day3hourData>, response: Response<Forecast5day3hourData>) {
                onSuccess()
                forecast3HourlyData.postValue(response.body())
            }

        })
    }

    override fun getGoogleImageUrl(cityId: Int, size: Pair<Int, Int>): String? {
        currentWeatherData.value?.list?.let {
            for (item in it) {
                if (item.id == cityId) {
                    return googlemapImageUrl(lat = item.coord.lat, lon = item.coord.lon, size = size)
                }
            }
        }
        return null
    }

}
