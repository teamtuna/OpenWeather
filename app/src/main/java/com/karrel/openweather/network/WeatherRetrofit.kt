package com.karrel.openweather.network

import com.karrel.openweather.constant.BASE_URL
import com.karrel.openweather.model.Forecast5day3hourData
import com.karrel.openweather.model.HourlyForecastData
import com.karrel.openweather.model.weather.CurrentWeather
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRetrofit : WeatherApi {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: WeatherApi = retrofit.create(WeatherApi::class.java)

    override fun currentWeatherData(cityId: Int, appId: String): Call<CurrentWeather> {
        println("WeatherRetrofit > currentWeatherData")
        return service.currentWeatherData(cityId, appId)
    }

    override fun hourlyForecast(cityId: Int, appId: String, cnt: Int): Call<HourlyForecastData> {
        println("WeatherRetrofit > hourlyForecast")
        return service.hourlyForecast(cityId, appId)
    }

    override fun forecast5day3hour(cityId: Int, appId: String): Call<Forecast5day3hourData> {
        println("WeatherRetrofit > forecast5day3hour")
        return service.forecast5day3hour(cityId, appId)
    }

    override fun findCurrentWeatherData(
        lat: Double,
        lon: Double,
        cityCnt: Int,
        appId: String
    ) = service.findCurrentWeatherData(lat, lon, cityCnt, appId)
}