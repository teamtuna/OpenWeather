package com.karrel.openweather.extension

import org.junit.Test

class WeatherExtensionKtTest {

    @Test
    fun ktoC() {
        val temp = 300.0
        assert(temp.KtoC() == "26.9°")
    }

    @Test
    fun toAmPm() {
        // utc : 1555834389546
        // date : 2019-04-21 17:13:09
        val value = 1555834389546.toAmPm()
        println("1555834389546 to $value")
        assert(value == "05:13 PM")
    }

    @Test
    fun IntToAmPm() {
        // utc : 1555834389546
        // date : 2019-04-21 17:13:09
        val value = 1555834389.toAmPm()
        println("1555834389 to $value")
        assert(value == "05:13 PM")
    }

    @Test
    fun toAmPmHour() {
        // utc : 1555834389546, unix : 1555834389L
        // date : 2019-04-21 17:13:09
        val value = 1555834389546.toAmPmHour()
        println("1555834389546 to $value")

        assert(value == "05 PM")
    }

    @Test
    fun IntToAmPmHour() {
        // utc : 1555834389546, unix : 1555834389L
        // date : 2019-04-21 17:13:09
        val value = 1555834389.toAmPmHour()
        println("1555834389 to $value")

        assert(value == "05 PM")
    }

    @Test
    fun toAmPmHour2() {
        // utc : 1555834389546, unix : 1555834389L
        // date : 2019-04-21 17:13:09
        val value = 1555834389546.toAmPmHour2()
        println("1555834389546 to $value")

        assert(value == "PM 05")
    }
    @Test
    fun InttoAmPmHour2() {
        // utc : 1555834389546, unix : 1555834389L
        // date : 2019-04-21 17:13:09
        val value = 1555834389.toAmPmHour2()
        println("1555834389 to $value")

        assert(value == "PM 05")
    }

    @Test
    fun toEkkmm() {
        // utc : 1555834389546, unix : 1555834389L
        // date : 2019-04-21 17:13:09
        val value = 1555834389546.toEkkmm()
        println("1555834389546 to $value")

        assert(value == "Sun 17:13")
    }

    @Test
    fun InttoEkkmm() {
        // utc : 1555834389546, unix : 1555834389L
        // date : 2019-04-21 17:13:09
        val value = 1555834389.toEkkmm()
        println("1555834389 to $value")

        assert(value == "Sun 17:13")
    }

    @Test
    fun `오전 0시 테스트`() {
        val h21 = 1555848000
        val h00 = 1555858800
        val h03 = 1555869600

        assert(h21.toAmPm() == "09:00 PM")
        assert(h00.toAmPm() == "00:00 AM")
        assert(h03.toAmPm() == "03:00 AM")

        println("h21.toAmPmHour() : ${h21.toAmPmHour()}")
        println("h00.toAmPmHour() : ${h00.toAmPmHour()}")
        println("h03.toAmPmHour() : ${h03.toAmPmHour()}")
        assert(h21.toAmPmHour() == "09 PM")
        assert(h00.toAmPmHour() == "00 AM")
        assert(h03.toAmPmHour() == "03 AM")

        println("h21.toAmPmHour2() : ${h21.toAmPmHour2()}")
        println("h00.toAmPmHour2() : ${h00.toAmPmHour2()}")
        println("h03.toAmPmHour2() : ${h03.toAmPmHour2()}")
        assert(h21.toAmPmHour2() == "PM 09")
        assert(h00.toAmPmHour2() == "AM 00")
        assert(h03.toAmPmHour2() == "AM 03")

        println("h21.toEkkmm() : ${h21.toEkkmm()}")
        println("h00.toEkkmm() : ${h00.toEkkmm()}")
        println("h03.toEkkmm() : ${h03.toEkkmm()}")
        assert(h21.toEkkmm() == "Sun 21:00")
        assert(h00.toEkkmm() == "Mon 00:00")
        assert(h03.toEkkmm() == "Mon 03:00")
    }
}