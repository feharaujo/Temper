package com.fearaujo.temper

import android.app.Application
import com.fearaujo.data.di.BASE_URL
import com.fearaujo.data.di.NetworkModule
import com.fearaujo.data.repository.di.RepositoryModule
import com.fearaujo.temper.dashboard.di.DashboardModule
import com.fearaujo.temper.ui.di.UIModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val modules = listOf(
            UIModule.imageModule,
            UIModule.glideModule,
            NetworkModule.setUpNetworkDependencies(BASE_URL),
            RepositoryModule.repositoryModule,
            DashboardModule.module
        )

        startKoin {
            printLogger()
            androidContext(this@AppApplication)
            modules(modules)
        }
    }

}