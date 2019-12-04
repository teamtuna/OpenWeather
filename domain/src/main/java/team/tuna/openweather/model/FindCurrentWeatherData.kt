package team.tuna.openweather.model

import team.tuna.openweather.model.weather.CurrentWeather

/**
 * 특정 위치 (위도,경도)를 기반한 그 주변 도시에 대한 날씨 정보 데이터
 */
class FindCurrentWeatherData {

    var message: String? = null
    var cod: String? = null
    var count: Int? = null
    var list: List<CurrentWeather>? = null
    override fun toString(): String {
        return "FindCurrentWeatherData(message=$message, cod=$cod, count=$count, list=$list)"
    }


}
