package com.fearaujo.data.repository.remote.deserializer

import com.fearaujo.model.Contractor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.InputStream
import java.lang.reflect.Type
import java.util.*

class ContractorDeserializerTest {

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
        val jsonFile = this.javaClass.classLoader!!.getResourceAsStream("Success_Response.json")
        val jsonString = convertStreamToString(jsonFile)

        val contractors = gson.fromJson<ArrayList<Contractor>>(jsonString, type)
        Assert.assertEquals("Drankrunner", contractors[0].title)
        Assert.assertEquals("Gezellige horecatijgers!", contractors[1].title)
    }

    private fun convertStreamToString(inputStream: InputStream): String {
        val s = Scanner(inputStream).useDelimiter("\\A")
        return if (s.hasNext()) s.next() else ""
    }

}