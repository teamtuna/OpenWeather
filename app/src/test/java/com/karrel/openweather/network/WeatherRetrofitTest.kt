package com.karrel.openweather.network

import org.junit.Test

class WeatherRetrofitTest {

    private val seoulCityId = 1835847

    @Test
    fun testCurrentWeatherSuccess() {
        val response = WeatherRetrofit.currentWeatherData(seoulCityId).execute()

        val data = response.body()

        println("data : $data")

        assert(response.isSuccessful)
    }

    @Test
    fun testHourlyForecastSuccess() {

        val response = WeatherRetrofit.hourlyForecast(seoulCityId).execute()

        val data = response.body()

        if (response.isSuccessful) {
            println("data : $data")
        } else {
            println("error body : ${response.message()}")
        }

        assert(response.isSuccessful)
    }

    @Test
    fun testForecast5day3hourDataSuccess() {
        val response = WeatherRetrofit.forecast5day3hour(seoulCityId).execute()

        val data = response.body()

        println("data : $data")

        assert(response.isSuccessful)
    }

    @Test
    fun testFindCurrentWeatherSuccess() {

        val response = WeatherRetrofit.findCurrentWeatherData().execute()

        val data = response.body()

        println("data : $data")

        assert(response.isSuccessful)
    }
}