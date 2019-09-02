package com.fearaujo.temper.dashboard.di

import androidx.paging.PagedList
import com.fearaujo.temper.dashboard.DashboardViewModel
import com.fearaujo.temper.dashboard.DashboardViewModelImpl
import com.fearaujo.temper.ui.PARAM_ITEM_DISTANCE
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

        viewModel<DashboardViewModel> {
            DashboardViewModelImpl(
                get() as PagedList.Config
            )
        }
    }


}