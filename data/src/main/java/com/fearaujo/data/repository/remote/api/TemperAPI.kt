package com.fearaujo.data.repository.remote.api

import com.fearaujo.data.di.PATH_CONTRACTORS
import com.fearaujo.data.di.QUERY_DATE_PARAM
import com.fearaujo.model.Contractor
import retrofit2.http.GET
import retrofit2.http.Query

interface TemperAPI {

    @GET(PATH_CONTRACTORS)
    suspend fun fetchContractors(@Query(QUERY_DATE_PARAM) date: String): ArrayList<Contractor>

}