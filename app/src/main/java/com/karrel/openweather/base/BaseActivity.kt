package com.karrel.openweather.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.crashlytics.android.Crashlytics
import com.karrel.openweather.event_bus.RxFinishBus
import com.karrel.openweather.event_bus.RxProgressBus
import com.karrel.openweather.event_bus.RxThrowableBus
import com.karrel.openweather.extension.plusAssign
import io.fabric.sdk.android.Fabric
import io.reactivex.android.schedulers.AndroidSchedulers
import karrel.com.mvvmsample.extensions.AutoClearedDisposable


/**
 * Created by Rell on 2019. 4. 19..
 */
open class BaseActivity : AppCompatActivity() {

    protected val disposables by lazy { AutoClearedDisposable(this) }
    private var isForeground = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Crashlytics.log(android.util.Log.DEBUG, "onCreate", javaClass.simpleName)
        lifecycle.addObserver(disposables)
        Fabric.with(this, Crashlytics())
        observeProgress()

        observeFinish()
        observeThrowable()
    }

    override fun onResume() {
        super.onResume()
        isForeground = true
    }

    override fun onPause() {
        super.onPause()
        isForeground = false
        hideProgressDialog()
    }

    private fun observeThrowable() {
        disposables += RxThrowableBus.observableException()
                .observeOn(AndroidSchedulers.mainThread())
                .filter { isForeground }
                .subscribe {
                    throwableProcess(it)
                }
    }

    private fun observeFinish() {
        disposables += RxFinishBus.observableFinish().subscribe { finish() }
    }

    private fun observeProgress() {
        disposables += RxProgressBus.observeProgress().observeOn(AndroidSchedulers.mainThread())
                .filter { isForeground }
                .subscribe {
                    println("observeProgress() : $it")
                    if (it) showProgressDialog()
                    else hideProgressDialog()
                }
    }

    protected open fun hideProgressDialog() {}

    protected open fun showProgressDialog() {}

    protected open fun throwableProcess(throwable: Throwable) {
        println("throwable > $throwable")
        Toast.makeText(applicationContext, throwable.message, Toast.LENGTH_LONG).show()
    }
}