package com.ianJung.doItStatus.sharedpre

import android.app.Application

class App :Application() {

    companion object{
        lateinit var prefs : MysharedPreferences
        var gold = 0
        var exp = 0

    }

    override fun onCreate() {
        prefs = MysharedPreferences(applicationContext)
        super.onCreate()
    }
}