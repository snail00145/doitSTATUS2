package com.ianJung.doItStatus.singlebungle

import android.app.Application

class Singleton : Application() {
    companion object{
        var gold : Long?= 0
        var exp : Long?= 0
    }
}