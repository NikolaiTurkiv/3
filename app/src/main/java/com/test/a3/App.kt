package com.test.a3

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.onesignal.OneSignal
import com.test.a3.di.DaggerAppComponent

class App: Application() {

    companion object {
        private const val ONESIGNAL_APP_ID = "357537be-074c-4cb6-87a8-aa3bb67bded6"
    }

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}