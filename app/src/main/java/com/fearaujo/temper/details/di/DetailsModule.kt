package com.fearaujo.temper.details.di

import com.fearaujo.temper.details.DetailsViewModel
import com.fearaujo.temper.details.DetailsViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DetailsModule {

    val module = module {
        viewModel<DetailsViewModel> { DetailsViewModelImpl() }
    }

}