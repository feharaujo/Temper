package com.fearaujo.temper.dashboard.di

import androidx.paging.PagedList
import com.fearaujo.data.repository.RepositorySourceFactory
import com.fearaujo.data.repository.remote.RemoteRepository
import com.fearaujo.temper.dashboard.DashboardViewModel
import com.fearaujo.temper.dashboard.DashboardViewModelImpl
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DashboardModule {

    val module = module {

        single {
            PagedList.Config.Builder()
                .setPrefetchDistance(PARAM_ITEM_DISTANCE)
                .setEnablePlaceholders(true)
                .build()
        }

        single { (scope: CoroutineScope) ->
            RepositorySourceFactory(
                get() as RemoteRepository,
                scope
            )
        }

        viewModel<DashboardViewModel> {
            DashboardViewModelImpl(
                get() as PagedList.Config
            )
        }
    }


}