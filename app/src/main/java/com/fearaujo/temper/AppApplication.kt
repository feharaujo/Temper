package com.fearaujo.temper

import android.app.Application
import com.fearaujo.data.di.NetworkModule
import com.fearaujo.data.repository.di.RepositoryModule
import com.fearaujo.temper.dashboard.di.DashboardModule
import com.fearaujo.temper.ui.di.UIModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val modules = listOf(DashboardModule.module,
                UIModule.module,
                NetworkModule.module,
                RepositoryModule.module
        )

        startKoin {
            printLogger()
            androidContext(this@AppApplication)
            modules(modules)
        }
    }

}