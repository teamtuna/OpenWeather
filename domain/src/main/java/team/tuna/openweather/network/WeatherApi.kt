package team.tuna.openweather.network

import team.tuna.openweather.constant.APP_ID
import team.tuna.openweather.constant.DEFAULT_CITY_CNT
import team.tuna.openweather.constant.DEFAULT_HOULY_CNT
import team.tuna.openweather.model.FindCurrentWeatherData
import team.tuna.openweather.model.Forecast5day3hourData
import team.tuna.openweather.model.HourlyForecastData
import team.tuna.openweather.model.weather.CurrentWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?")
    fun currentWeatherData(
            @Query("id") cityId: Int,
            @Query("APPID") appId: String = APP_ID

    ): Call<CurrentWeather>

    @GET("forecast/hourly?")
    fun hourlyForecast(
        @Query("id") cityId: Int,
        @Query("APPID") appId: String = APP_ID,
        @Query("cnt") cnt: Int = DEFAULT_HOULY_CNT

    ): Call<HourlyForecastData>

    @GET("forecast?")
    fun forecast5day3hour(
            @Query("id") cityId: Int,
            @Query("APPID") appId: String = APP_ID

    ): Call<Forecast5day3hourData>

    //    http://api.openweathermap.org/data/2.5/find?lat=55.5&lon=37.5&cnt=10&APPID=fe7751427b8239af2e26cd80f435b12b
    @GET("find?")
    fun findCurrentWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("cnt") cityCnt: Int = DEFAULT_CITY_CNT,
        @Query("APPID") appId: String = APP_ID

    ): Call<FindCurrentWeatherData>


}