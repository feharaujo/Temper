package com.fearaujo.temper

import android.content.Context
import com.bumptech.glide.RequestManager
import com.fearaujo.data.di.NetworkModule
import com.fearaujo.data.repository.di.RepositoryModule
import com.fearaujo.temper.dashboard.di.DashboardModule
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.CoroutineScope
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class KoinDependenciesTest : KoinTest {

    @Mock
    private lateinit var requestManagerMock: RequestManager
    @Mock
    private lateinit var contextMock: Context
    @Mock
    private lateinit var applicationMock: AppApplication
    @Mock
    private lateinit var coroutineScopeMock: CoroutineScope

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `all dependencies should be satisfied`() {
        val mockModule = module(override = true) {
            single { contextMock }
            single { applicationMock }
            single { requestManagerMock }
            single { coroutineScopeMock }
        }

        val modules = listOf(
            mockModule,
            DashboardModule.module,
            NetworkModule.module,
            RepositoryModule.module
        )

        startKoin {
            printLogger()
            androidContext(mock())
            modules(modules)
        }

        getKoin().checkModules()
    }
}
