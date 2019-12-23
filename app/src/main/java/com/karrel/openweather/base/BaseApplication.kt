package com.karrel.openweather.base

import android.app.Application
import android.content.Context
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import team.tuna.openweather.constant.appContext
import team.tuna.openweather.repository.LocationRepository

class BaseApplication : Application() {

    companion object {
        lateinit var instance: BaseApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()

        LocationRepository.create(this)
        Fabric.with(this, Crashlytics())
        Crashlytics.log("시작됨")

        instance = this
        appContext = applicationContext
    }

    fun context(): Context = applicationContext

}