package com.ascend5050.paxposlink

import android.app.Application
import com.pax.poslink.POSLinkAndroid

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        POSLinkAndroid.init(applicationContext)
    }
}