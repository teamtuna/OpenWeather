package com.karrel.openweather.view

import android.os.Bundle
import com.karrel.openweather.R
import com.karrel.openweather.base.BaseActivity
import com.karrel.openweather.event_bus.RxFinishBus
import com.karrel.openweather.extension.plusAssign
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class IntroActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_intro)

        disposables += Observable.timer(3L, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    finish()
                }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        RxFinishBus.finish()
    }

}