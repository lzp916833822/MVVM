package com.eloam.mvvm

import android.app.Application
import android.content.Context
import com.eloam.mvvm.local.AppDatabase
import com.eloam.mvvm.local.LocalInjector
import com.eloam.mvvm.ui.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

class MyApplication : Application() {

    companion object {
        var context: Application? = null

        fun getApplication(): Context {
            return context!!
        }

    }

    init {
        context = this
    }

    override fun onCreate() {
        super.onCreate()
        LocalInjector.appDatabase = AppDatabase.getInstance(this@MyApplication)

        startKoin {
            AndroidLogger()
            androidContext(this@MyApplication)
            koinApplication { }
            modules(appModule)
        }


    }
}