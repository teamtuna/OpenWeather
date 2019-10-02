package com.karrel.openweather.model

import com.karrel.openweather.model.weather.City
import com.karrel.openweather.model.weather.CurrentWeather

/**
 * 시간별 데이터 (4일치)
 */
class HourlyForecastData {

    var cod: String? = null
    var message: Float? = null
    var cnt: Int? = null
    var list: List<CurrentWeather>? = null
    var city: City? = null

    override fun toString(): String {
        return "HourlyForecastData(cod=$cod, message=$message, cnt=$cnt, list=$list, city=$city)"
    }


}
