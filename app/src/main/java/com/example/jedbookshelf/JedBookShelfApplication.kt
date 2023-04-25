package com.example.jedbookshelf

import android.app.Application
import com.example.jedbookshelf.data.AppContainer
import com.example.jedbookshelf.data.DefaultAppContainer

class JedBookShelfApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()

    }

}