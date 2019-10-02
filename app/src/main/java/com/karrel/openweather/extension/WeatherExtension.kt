package com.karrel.openweather.extension

import com.karrel.openweather.R
import java.text.SimpleDateFormat
import java.util.*


/**
 * 적용된 로티 이미지 이름 반환
 */
fun String.toLottieIcon(): String {
    return when {
        this.startsWith("01") -> "w_01_clear_sky.json"
        this.startsWith("02") -> "w_02_few_clouds.json"
        this.startsWith("03") -> "w_03_clouds_d.json"
        this.startsWith("04") -> "w_04_broken_clouds.json"
        this.startsWith("09") -> "w_09_shower_rain.json"
        this.startsWith("10") -> "w_10_rain.json"
        this.startsWith("11") -> "w_11_thunderstorm.json"
        this.startsWith("13") -> "w_13_snow.json"
        this.startsWith("50") -> "w_50_fog.json"
        else -> "w_01_clear_sky.json"
    }
}

fun String.toWeatherIcon(): Int {
    return when {
        this.startsWith("01") -> R.drawable.icon_clear_sky
        this.startsWith("02") -> R.drawable.icon_few_clouds
        this.startsWith("03") -> R.drawable.icon_clouds
        this.startsWith("04") -> R.drawable.icon_broken_clouds
        this.startsWith("09") -> R.drawable.icon_shower_rain
        this.startsWith("10") -> R.drawable.icon_rain
        this.startsWith("11") -> R.drawable.icon_thunderstorm
        this.startsWith("13") -> R.drawable.icon_snow
        this.startsWith("50") -> R.drawable.icon_fog
        else -> R.drawable.icon_clear_sky
    }
}

/**
 * 절대온도(K)에서 섭씨(C)로 변환
 */
fun Double.KtoC(): String {
    val c = this - 273.15
    return String.format("%.1f°", c)
}

/**
 * UTC(Unix) to Locale "hh:mm aa"
 * ex) 1555834389000 to "05:13 PM"
 */
fun Int.toAmPm(): String {
    return (this * 1000L).toAmPm()
}

/**
 * UTC(Unix) to Locale "hh:mm aa"
 * ex) 1555834389546L to "05:13 PM"
 */
fun Long.toAmPm(): String {
    val date = Date(this)
    val dateFormat = SimpleDateFormat("hh:mm aa")
    val ret = dateFormat.format(date)
    if (ret.startsWith("12")) {
        return ret.replace("12", "00")
    }
    return ret
}

/**
 * UTC(Unix) to Locale "hh:mm aa"
 * ex) 1555834389000 to "05 PM"
 */
fun Int.toAmPmHour(): String {
    return (this * 1000L).toAmPmHour()
}

/**
 * UTC(Unix) to Locale "hh a"
 * ex) 1555834389546L to "05 PM"
 */
fun Long.toAmPmHour(): String {
    val date = Date(this)
    val dateFormat = SimpleDateFormat("hh a")
    val ret = dateFormat.format(date)
    if (ret.startsWith("12")) {
        return ret.replace("12", "00")
    }
    return ret
}

/**
 * UTC(Unix) to Locale "hh:mm aa"
 * ex) 1555834389000 to "PM 05"
 */
fun Int.toAmPmHour2(): String {
    return (this * 1000L).toAmPmHour2()
}

/**
 * UTC(Unix) to Locale "a hh"
 * ex) 1555834389546L to "PM 05"
 */
fun Long.toAmPmHour2(): String {
    var date = Date(this)
    val dateFormat = SimpleDateFormat("a hh")

    val ret = dateFormat.format(date)
    if (ret.endsWith("12")) {
        return ret.replace("12", "00")
    }
    return ret
}

/**
 * UTC(Unix) to Locale "hh:mm aa"
 * ex) 1555834389000 to "Sun 17:13"
 */
fun Int.toEkkmm(): String {
    return (this * 1000L).toEkkmm()
}

/**
 * UTC(Unix) to Locale "E kk:mm"
 * ex) 1555834389546L to "Sun 17:13"
 */
fun Long.toEkkmm(): String {
    val date = Date(this)
    val dateFormat = SimpleDateFormat("E kk:mm")
    val ret = dateFormat.format(date)
    if (ret.contains("24:")) {
        return ret.replace("24:", "00:")
    }
    return ret
}