package com.fearaujo.data.repository.remote.deserializer

import com.fearaujo.data.di.DATA_NODE_KEY
import com.fearaujo.model.Contractor
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ContractorDeserializer : JsonDeserializer<ArrayList<Contractor>> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ArrayList<Contractor> {
        val contractorsList = ArrayList<Contractor>()

        json?.let { rootElement ->
            val rootObject = rootElement.asJsonObject
            val dataObject = rootObject.getAsJsonObject(DATA_NODE_KEY)

            // since the date is specified as a parameter it's just necessary to get just the first object
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