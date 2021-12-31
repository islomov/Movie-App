package com.example.movieapp

import android.app.Application
import android.util.Log
import com.example.movieapp.koin.applicationComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OurApp: Application() {

    override fun onCreate() {
        super.onCreate()
        setUpKoin()
        Log.e("Islomov","")
    }

    private fun setUpKoin() {
        startKoin {
            androidContext(this@OurApp)
            modules(applicationComponent)
        }
    }
}