package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.UserDI
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // declare used Android context
            androidContext(this@CustomApplication)
            modules(UserDI.userModules)
        }
    }
}