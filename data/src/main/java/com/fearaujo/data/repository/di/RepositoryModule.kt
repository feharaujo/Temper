package com.fearaujo.data.repository.di

import com.fearaujo.data.repository.remote.RemoteRepository
import com.fearaujo.data.repository.remote.RemoteRepositoryImpl
import com.fearaujo.data.repository.remote.api.TemperAPI
import org.koin.dsl.module
import retrofit2.Retrofit

object RepositoryModule {

    val module = module {
        single<RemoteRepository> {
            RemoteRepositoryImpl(
                    get() as TemperAPI
            )
        }

        single {
            val retrofit = get() as Retrofit
            retrofit.create(TemperAPI::class.java)
        }
    }

}
