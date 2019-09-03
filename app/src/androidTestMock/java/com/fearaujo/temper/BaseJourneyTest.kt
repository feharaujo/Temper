package com.fearaujo.temper

import com.example.mockwebserver.BaseMockWebServerTest
import com.fearaujo.data.di.NetworkModule
import com.fearaujo.data.repository.di.RepositoryModule
import com.fearaujo.temper.common.DisableAnimationsRule
import com.fearaujo.temper.dashboard.di.DashboardModule
import com.fearaujo.temper.ui.di.UIModule
import org.junit.Rule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

open class BaseJourneyTest : BaseMockWebServerTest() {

    @Rule
    @JvmField
    val disableAnimationRule = DisableAnimationsRule()

    override fun setUp() {
        super.setUp()

        val modules = listOf(
            UIModule.imageModule,
            UIModule.glideModule,
            NetworkModule.setUpNetworkDependencies(getMockUrl()),
            RepositoryModule.repositoryModule,
            DashboardModule.module
        )

        startKoin {
            modules(modules)
        }
    }

    override fun tearDown() {
        stopKoin()
        super.tearDown()
    }
}