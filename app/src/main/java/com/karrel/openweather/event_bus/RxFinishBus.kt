package com.karrel.openweather.event_bus

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

object RxFinishBus {
    private val finishBusObservable by lazy { PublishSubject.create<Unit>() }

    fun observableFinish(): Observable<Unit> = finishBusObservable.observeOn(AndroidSchedulers.mainThread())

    fun finish() {
        finishBusObservable.onNext(Unit)
    }
}