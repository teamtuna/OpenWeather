package team.tuna.openweather.network

import team.tuna.openweather.constant.BASE_URL
import team.tuna.openweather.constant.appContext
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import team.tuna.openweather.model.Forecast5day3hourData
import team.tuna.openweather.model.HourlyForecastData
import team.tuna.openweather.model.weather.CurrentWeather

object WeatherRetrofit : WeatherApi {

    private val client = OkHttpClient.Builder()
        .addInterceptor(ChuckInterceptor(appContext))
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val service: WeatherApi = retrofit.create(
        WeatherApi::class.java
    )

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