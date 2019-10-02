package com.karrel.openweather.event_bus

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

object RxThrowableBus {
    private val exceptionObservable by lazy { PublishSubject.create<Throwable>() }

    fun observableException(): Observable<Throwable> = exceptionObservable.observeOn(AndroidSchedulers.mainThread())

    fun sendThrowable(throwable: Throwable) {
        exceptionObservable.onNext(throwable)
    }
}