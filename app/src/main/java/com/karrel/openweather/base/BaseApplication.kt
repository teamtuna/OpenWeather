package com.karrel.openweather.base

import android.app.Application
import android.content.Context

class BaseApplication : Application() {

    companion object {
        lateinit var instance: BaseApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    fun context(): Context = applicationContext

}