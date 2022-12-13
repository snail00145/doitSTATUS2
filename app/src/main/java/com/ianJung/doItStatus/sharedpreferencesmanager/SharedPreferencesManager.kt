package com.ianJung.doItStatus.sharedpreferencesmanager

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager {
    companion object{
        private var sp : SharedPreferences?=null
        private var editor : SharedPreferences.Editor?=null

        private fun getInstance(context: Context):SharedPreferences{
            synchronized(this){
                sp = context.getSharedPreferences("SharedPreferencesFile", Context.MODE_PRIVATE)
                return sp!!
            }
        }

        fun putGold(context : Context){
            editor = getInstance(context).edit()
            editor!!.putInt("gold", 0)
            editor!!.apply()
        }

        fun getGold(context: Context) : Int{
            return getInstance(context).getInt("gold", 0)
        }



    }




}