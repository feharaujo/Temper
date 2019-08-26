package com.fearaujo.temper.ui.di

import android.content.Context
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import org.koin.dsl.module

object UIModule {
    val module = module {
        single {
            Picasso.Builder(get() as Context)
                    .downloader(get() as OkHttp3Downloader)
                    .indicatorsEnabled(true)
                    .build()
        }
    }
}