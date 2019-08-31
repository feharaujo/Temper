package com.fearaujo.temper.ui.di

import android.content.Context
import com.bumptech.glide.Glide
import com.fearaujo.temper.ui.image.ImageLoader
import org.koin.dsl.module

object UIModule {
    val module = module {
        factory { (context: Context) -> ImageLoader(Glide.with(context)) }
    }
}