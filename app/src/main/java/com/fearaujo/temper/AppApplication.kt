package com.fearaujo.temper

import android.app.Application
import com.fearaujo.data.di.RepositoryModule
import com.fearaujo.temper.dashboard.di.DashboardModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val modules = listOf(DashboardModule.module, RepositoryModule.networkModule, RepositoryModule.repositoryModule)

        startKoin {
            printLogger()
            androidContext(this@AppApplication)
            modules(modules)
        }
    }

}