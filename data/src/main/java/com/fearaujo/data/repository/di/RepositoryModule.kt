package com.fearaujo.data.repository.di

import com.fearaujo.data.repository.RepositorySourceFactory
import com.fearaujo.data.repository.remote.RemoteRepository
import com.fearaujo.data.repository.remote.RemoteRepositoryImpl
import com.fearaujo.data.repository.remote.api.TemperAPI
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

object RepositoryModule {

    val repositoryModule = module {
        single<RemoteRepository> {
            RemoteRepositoryImpl(
                get() as TemperAPI
            )
        }

        single { (scope: CoroutineScope) ->
            RepositorySourceFactory(
                get() as RemoteRepository,
                scope
            )
        }
    }

}