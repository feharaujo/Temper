package com.fearaujo.data.repository.remote

import com.fearaujo.data.repository.remote.PageDateConverter.formatDate
import com.fearaujo.data.repository.remote.PageDateConverter.increaseDateInDays
import com.fearaujo.data.repository.remote.api.TemperAPI
import com.fearaujo.model.Contractor
import java.util.*

interface RemoteRepository {
    suspend fun fetchContractorsByDate(page: Int): ArrayList<Contractor>
}

class RemoteRepositoryImpl(private val temperAPI: TemperAPI) : RemoteRepository {

    private val currentDate = Date()

    override suspend fun fetchContractorsByDate(page: Int): ArrayList<Contractor> {
        val formattedDate = formatDate(increaseDateInDays(currentDate, page))
        return temperAPI.fetchContractors(formattedDate)
    }

}