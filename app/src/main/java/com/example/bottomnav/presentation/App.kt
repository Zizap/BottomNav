package com.example.bottomnav.presentation

import android.app.Application
import com.example.bottomnav.presentation.di.moduleCa
import com.example.bottomnav.presentation.di.moduleDBCa
import com.example.bottomnav.presentation.di.moduleDBProd
import com.example.bottomnav.presentation.di.moduleProd
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@App)

            modules(moduleDBProd, moduleProd, moduleDBCa, moduleCa)
        }

    }

}