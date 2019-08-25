package com.fearaujo.data.base

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.test.KoinTest
import java.io.File
import java.io.InputStream
import java.util.*

abstract class BaseMockWebServerTest : KoinTest {

    protected lateinit var mockServer: MockWebServer

    @Before
    open fun setUp() {
        configureMockServer()
    }

    @After
    open fun tearDown() {
        stopMockServer()
    }

    private fun configureMockServer() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    private fun stopMockServer() {
        mockServer.shutdown()
    }

    fun getMockUrl() = mockServer.url("/").toString()

    fun mockHttpResponse(fileName: String, responseCode: Int) = mockServer.enqueue(
            MockResponse()
                    .setResponseCode(responseCode)
                    .setBody(getJson(fileName)))

    private fun getJson(path: String): String {
        val jsonFile = this.javaClass.classLoader!!.getResourceAsStream(path)
        return convertStreamToString(jsonFile)
    }

    // TODO: refactor
    private fun convertStreamToString(inputStream: InputStream): String {
        val s = Scanner(inputStream).useDelimiter("\\A")
        return if (s.hasNext()) s.next() else ""
    }
}