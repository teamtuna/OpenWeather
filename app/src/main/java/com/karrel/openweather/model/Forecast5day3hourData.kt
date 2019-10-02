package com.karrel.openweather.model

import com.karrel.openweather.model.weather.City
import com.karrel.openweather.model.weather.CurrentWeather

class Forecast5day3hourData {

    var cod: String? = null
    var message: Float? = null
    var cnt: Int? = null
    var list: List<CurrentWeather>? = null
    var city: City? = null

    override fun toString(): String {
        return "Forecast5day3hourData{" +
                "city=" + city +
                ", cnt=" + cnt +
                ", cod='" + cod + '\''.toString() +
                ", list=" + list +
                ", message=" + message +
                '}'.toString()
    }
}
