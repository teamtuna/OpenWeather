package com.karrel.openweather.network

import org.junit.Test

/**
 * Created by Rell on 2019. 4. 18..
 */
class GoogleMapApiKtTest {

    @Test
    fun testGooglemapImageUrl() {

        val url =
            "https://maps.googleapis.com/maps/api/staticmap?zoom=13&size=600x300&maptype=roadmap&markers=color:red%7Clabel:C%7C37.394986,127.109018&key=AIzaSyDP9laAKtbDd22DM9eq9NBum9wWzt-90es"

        val googleUrl = googlemapImageUrl(lat = 37.394986, lon = 127.109018)

        assert(googleUrl == url)
    }
}