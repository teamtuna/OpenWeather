package team.tuna.openweather.repository

import team.tuna.openweather.model.FindCurrentWeatherData

/**
 * Created by Rell on 2019. 4. 18..
 */
interface WeatherDataSource {
    fun loadWeatherListData(
        lat: Double,
        lon: Double, onSuccess: () -> Unit, onFailure: (t: Throwable) -> Unit
    )

    fun loadCurrentCityData(cityId: Int, onSuccess: () -> Unit, onFailure: (t: Throwable) -> Unit)
    fun loadHourlyForecastData(
        cityId: Int,
        onSuccess: () -> Unit,
        onFailure: (t: Throwable) -> Unit
    )

    fun load3HourlyForecastData(
        cityId: Int,
        onSuccess: () -> Unit,
        onFailure: (t: Throwable) -> Unit
    )

    fun getCurrentWeatherData(): FindCurrentWeatherData?
}