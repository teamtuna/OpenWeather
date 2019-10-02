package com.karrel.openweather.event_bus

import org.junit.Test

import org.junit.Assert.*

class RxProgressBusTest {

    @Test
    fun showProgress() {
        RxProgressBus.observeProgress()
            .subscribe {
                println("it : $it")
                assertTrue(it)
            }

        RxProgressBus.showProgress()
    }

    @Test
    fun hideProgress() {
        RxProgressBus.observeProgress()
            .subscribe {
                println("it : $it")
                assertFalse(it)
            }

        RxProgressBus.hideProgress()
    }
}