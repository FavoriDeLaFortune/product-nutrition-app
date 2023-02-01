package com.example.productnutritionapp

import android.app.Application
import com.example.productnutritionapp.di.networkModule
import com.example.productnutritionapp.di.viewModelModule
import com.google.android.material.color.DynamicColors
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        DynamicColors.applyToActivitiesIfAvailable(this)

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(networkModule, viewModelModule)
        }
    }
}