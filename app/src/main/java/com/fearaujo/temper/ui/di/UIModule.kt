package com.fearaujo.temper.ui.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.fearaujo.temper.ui.image.ImageLoader
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

object UIModule {

    val imageModule = module {
        factory { (context: Context) -> ImageLoader(get { parametersOf(context) }) }
    }

    /**
     * Glide has a separate module since @{RequestManager} has Android dependencies, so it's not possible to unit test.
     * For this reason, this module is overridden with a mock
     */
    val glideModule = module {
        factory<RequestManager> { (context: Context) -> Glide.with(context) }
    }

}