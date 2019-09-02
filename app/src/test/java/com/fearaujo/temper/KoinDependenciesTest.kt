package com.fearaujo.temper

import android.content.Context
import com.bumptech.glide.RequestManager
import com.fearaujo.data.di.NetworkModule
import com.fearaujo.data.repository.RepositorySourceFactory
import com.fearaujo.data.repository.di.RepositoryModule
import com.fearaujo.temper.dashboard.di.DashboardModule
import com.fearaujo.temper.ui.di.UIModule
import com.fearaujo.temper.ui.image.ImageLoader
import kotlinx.coroutines.CoroutineScope
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class KoinDependenciesTest : KoinTest {

    private companion object {
        const val MOCK_BASE_URL = "http://127.0.0.1/"
    }

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

        val mockModule = module(override = true) {
            single { contextMock }
            single { applicationMock }
            single { coroutineScopeMock }
            single { requestManagerMock }
        }

        val modules = listOf(
            mockModule,
            UIModule.imageModule,
            NetworkModule.setUpNetworkDependencies(MOCK_BASE_URL),
            DashboardModule.module,
            RepositoryModule.repositoryModule
        )

        startKoin {
            printLogger()
            androidContext(contextMock)
            modules(modules)
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `all dependencies should be satisfied`() {
        getKoin().checkModules {
            create<ImageLoader> {
                parametersOf(contextMock)
            }

            create<RepositorySourceFactory> {
                parametersOf(
                    coroutineScopeMock
                )
            }

        }
    }
}