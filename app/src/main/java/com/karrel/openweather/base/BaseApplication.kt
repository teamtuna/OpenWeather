package com.karrel.openweather.base

import android.app.Application
import android.content.Context
import team.tuna.openweather.constant.appContext

class BaseApplication : Application() {

    companion object {
        lateinit var instance: BaseApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        appContext = applicationContext
    }

    fun context(): Context = applicationContext

}