package com.karrel.openweather.extension

import io.reactivex.disposables.Disposable
import karrel.com.mvvmsample.extensions.AutoClearedDisposable

operator fun AutoClearedDisposable.plusAssign(disposable: Disposable) = this.add(disposable)