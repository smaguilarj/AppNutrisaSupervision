package com.example.nutrisaapplication.data

import android.app.Application

class SharedApp : Application() {
    companion object {
        lateinit var prefs: Preference
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Preference(applicationContext)
    }
}