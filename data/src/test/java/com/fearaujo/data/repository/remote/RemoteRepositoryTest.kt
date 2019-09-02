package com.fearaujo.data.repository.remote

import com.fearaujo.data.base.BaseMockWebServerTest
import com.fearaujo.data.di.NetworkModule
import com.fearaujo.data.repository.di.RepositoryModule
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.inject
import java.net.HttpURLConnection

class RemoteRepositoryTest : BaseMockWebServerTest() {

    private companion object {
        const val FILE_SUCCESS = "Success_Response.json"

        const val MOCK_TITLE_1 = "Drankrunner"
        const val MOCK_TITLE_2 = "Gezellige horecatijgers!"
    }

    private val repository by inject<RemoteRepository>()

    override fun setUp() {
        super.setUp()
        startKoin {
            modules(listOf(
                NetworkModule.setUpNetworkDependencies(getMockUrl()),
                RepositoryModule.repositoryModule
            ))
        }
    }

    override fun tearDown() {
        stopKoin()
        super.tearDown()
    }

    @Test
    fun `call and deserialize with success`() {
        mockHttpResponse(fileName = FILE_SUCCESS, responseCode = HttpURLConnection.HTTP_OK)

        runBlocking {
            val contractors = repository.fetchContractorsByDate(0)
            Assert.assertEquals(MOCK_TITLE_1, contractors[0].title)
            Assert.assertEquals(MOCK_TITLE_2, contractors[1].title)
        }
    }

    @Test(expected = Exception::class)
    fun `fail on response`() {
        mockHttpResponse(responseCode = HttpURLConnection.HTTP_BAD_REQUEST)

        runBlocking {
            val contractors = repository.fetchContractorsByDate(0)
            Assert.assertEquals(MOCK_TITLE_1, contractors[0].title)
            Assert.assertEquals(MOCK_TITLE_2, contractors[1].title)
        }
    }

}