package com.ianJung.doItStatus.sharedpre

import android.content.Context
import android.content.SharedPreferences

class MysharedPreferences(context: Context) {

    val prefs : SharedPreferences = context.getSharedPreferences("pref", 0)
    var editor = prefs.edit()

   fun getGold(): Int {
        return prefs.getInt("gold", 0)
    }

    fun getExp(): Int{
        return prefs.getInt("exp", 0)
    }

    fun getPetExp() : Int{
        return prefs.getInt("petExp", 0)
    }

}