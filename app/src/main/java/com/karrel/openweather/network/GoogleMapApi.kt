package com.karrel.openweather.network

import com.karrel.openweather.constant.GOOGLE_MAP_KEY


//https://maps.googleapis.com/maps/api/staticmap?center=Dongjinwon&zoom=13&size=600x300&maptype=roadmap&markers=color:red%7Clabel:C%7C37.394986,127.109018&key=AIzaSyDP9laAKtbDd22DM9eq9NBum9wWzt-90es

fun googlemapImageUrl(
    zoom: Int = 13,
    size: Pair<Int, Int> = Pair(300, 300),
    lat: Double,
    lon: Double,
    key: String = GOOGLE_MAP_KEY
): String {
    val baseUrl = "https://maps.googleapis.com/maps/api/staticmap?"
    val sSize = "${size.first}x${size.second}"
    val markers = "color:red%7Clabel:C%7C$lat,$lon"

    return "${baseUrl}zoom=$zoom&size=$sSize&maptype=roadmap&markers=$markers&key=$key"
}