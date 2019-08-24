package com.fearaujo.data.repository.remote.deserializer

import com.fearaujo.data.repository.remote.DATA_NODE_KEY
import com.fearaujo.model.Contractor
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ContractorDeserializer : JsonDeserializer<List<Contractor>> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): List<Contractor> {
        val contractorsList = mutableListOf<Contractor>()

        json?.let { rootElement ->
            val rootObject = rootElement.asJsonObject
            val dataObject = rootObject.getAsJsonObject(DATA_NODE_KEY)
            val contractorsArray = dataObject.entrySet().toTypedArray()[0].value.asJsonArray
            contractorsArray.forEach { contractorElement ->
                val contractor = context?.deserialize<Contractor>(contractorElement, Contractor::class.java)
                if (contractor != null) {
                    contractorsList.add(contractor)
                }
            }
        }

        return contractorsList
    }

}