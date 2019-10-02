package com.karrel.openweather.base

import androidx.lifecycle.ViewModel
import com.karrel.openweather.event_bus.RxProgressBus
import com.karrel.openweather.event_bus.RxThrowableBus

/**
 * Created by Rell on 2019. 4. 19..
 */
open class BaseViewModel : ViewModel() {
    protected fun showProgress() {
        RxProgressBus.showProgress()
    }

    protected fun hideProgress() {
        RxProgressBus.hideProgress()
    }

    protected fun throwMessage(throwable: Throwable) {

        RxThrowableBus.sendThrowable(throwable)
    }
}