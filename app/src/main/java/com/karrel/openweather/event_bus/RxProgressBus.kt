package com.karrel.openweather.event_bus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object RxProgressBus {
    private val progressBus by lazy { PublishSubject.create<Boolean>() }

    fun observeProgress(): Observable<Boolean> =progressBus

    fun showProgress() {
        progressBus.onNext(true)
    }

    fun hideProgress() {
        progressBus.onNext(false)
    }
}