package com.fearaujo.data.repository.remote

import com.fearaujo.data.base.BaseMockWebServerTest
import com.fearaujo.data.di.FakeRepositoryModule.setUpMockNetworkDependencies
import com.fearaujo.data.di.RepositoryModule
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.inject
import java.net.HttpURLConnection

class RemoteRepositoryTest : BaseMockWebServerTest() {

    private val repository by inject<RemoteRepository>()


    override fun setUp() {
        super.setUp()
        startKoin {
            modules(listOf(setUpMockNetworkDependencies(getMockUrl()), RepositoryModule.repositoryModule))
        }
    }

    @Test
    fun `call and deserialize with success`() {
        mockHttpResponse("Success_Response.json", HttpURLConnection.HTTP_OK)

        runBlocking {
            val contractors = repository.fetchContractorsByDate(0)
            Assert.assertEquals("Drankrunner", contractors[0].title)
            Assert.assertEquals("Gezellige horecatijgers!", contractors[1].title)
        }
    }

}