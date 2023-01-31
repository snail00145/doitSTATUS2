package com.ianJung.doItStatus.sharedpre

import android.R
import android.app.Application
import android.view.LayoutInflater
import android.widget.ImageView
import com.ianJung.doItStatus.databinding.FragmentStatBinding
import com.ianJung.doItStatus.ui.fragment.StatFragment
import kotlinx.coroutines.NonDisposableHandle.parent


class App :Application() {

    companion object{
        lateinit var prefs : MysharedPreferences
        var gold = 0
        var exp = 0
        var petexp = 0
        var lastAccessTime : Long=0

    }

    override fun onCreate() {
        prefs = MysharedPreferences(applicationContext)
        super.onCreate()

    }

}