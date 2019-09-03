package com.fearaujo.data.repository.remote.deserializer

import com.fearaujo.model.Contractor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.InputStream
import java.lang.reflect.Type
import java.util.*

class ContractorDeserializerTest {

    private companion object {
        const val FILE_SUCCESS = "Success_Response.json"

        const val MOCK_TITLE_1 = "Drankrunner"
        const val MOCK_TITLE_2 = "Gezellige horecatijgers!"
    }

    private lateinit var gson: Gson
    private lateinit var type: Type

    @Before
    fun setUp() {
        val gsonBuilder = GsonBuilder()
        type = object : TypeToken<ArrayList<Contractor>>() {}.type
        gsonBuilder.registerTypeAdapter(type, ContractorDeserializer())
        gson = gsonBuilder.create()
    }

    @Test
    fun `should deserialize with success from json file with custom deserializer`() {
        val jsonFile = this.javaClass.classLoader!!.getResourceAsStream(FILE_SUCCESS)
        val jsonString = convertStreamToString(jsonFile)

        val contractors = gson.fromJson<ArrayList<Contractor>>(jsonString, type)

        assertEquals(MOCK_TITLE_1, contractors[0].title)
        assertEquals(MOCK_TITLE_2, contractors[1].title)

        assertNotNull(contractors[0])
        assertNotNull(contractors[1])

        assertNotNull(contractors[0].jobCategory)
        assertNotNull(contractors[1].jobCategory)

        assertNotNull(contractors[0].shifts)
        assertNotNull(contractors[1].shifts)

        assertNotNull(contractors[0].client)
        assertNotNull(contractors[1].client)
    }

    private fun convertStreamToString(inputStream: InputStream): String {
        val s = Scanner(inputStream).useDelimiter("\\A")
        return if (s.hasNext()) s.next() else ""
    }

}