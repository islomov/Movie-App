package com.example.movieapp

import android.app.Application
import com.example.movieapp.koin.applicationComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OurApp: Application() {

    override fun onCreate() {
        super.onCreate()
        setUpKoin()
    }

    private fun setUpKoin() {
        startKoin {
            androidContext(this@OurApp)
            modules(applicationComponent)
        }
    }
}