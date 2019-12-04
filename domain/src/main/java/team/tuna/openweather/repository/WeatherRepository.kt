package team.tuna.openweather.repository

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.tuna.openweather.model.FindCurrentWeatherData
import team.tuna.openweather.model.Forecast5day3hourData
import team.tuna.openweather.model.HourlyForecastData
import team.tuna.openweather.model.weather.CurrentWeather
import team.tuna.openweather.network.WeatherRetrofit

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

            override fun onResponse(
                call: Call<HourlyForecastData>,
                response: Response<HourlyForecastData>
            ) {
                onSuccess()
                hourlyForecastData.postValue(response.body())
            }

        })
    }

    override fun loadWeatherListData(
        lat: Double,
        lon: Double,
        onSuccess: () -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {

        WeatherRetrofit.findCurrentWeatherData(lat, lon).enqueue(object :
            Callback<FindCurrentWeatherData> {
            override fun onFailure(call: Call<FindCurrentWeatherData>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(
                call: Call<FindCurrentWeatherData>,
                response: Response<FindCurrentWeatherData>
            ) {
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

            override fun onResponse(
                call: Call<CurrentWeather>,
                response: Response<CurrentWeather>
            ) {
                onSuccess()
                currentCityData.postValue(response.body())
            }

        })
    }

    override fun load3HourlyForecastData(
        cityId: Int,
        onSuccess: () -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        WeatherRetrofit.forecast5day3hour(cityId).enqueue(object : Callback<Forecast5day3hourData> {
            override fun onFailure(call: Call<Forecast5day3hourData>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(
                call: Call<Forecast5day3hourData>,
                response: Response<Forecast5day3hourData>
            ) {
                onSuccess()
                forecast3HourlyData.postValue(response.body())
            }

        })
    }


    override fun getCurrentWeatherData() = currentWeatherData.value

}
