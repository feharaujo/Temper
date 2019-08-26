package com.fearaujo.data.di

import android.util.Log
import com.fearaujo.data.repository.remote.deserializer.ContractorDeserializer
import com.fearaujo.model.Contractor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.OkHttp3Downloader
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    val module = module {

        single {
            val gsonBuilder = GsonBuilder()
            val type = object : TypeToken<ArrayList<Contractor>>() {}.type
            gsonBuilder.registerTypeAdapter(type, ContractorDeserializer())
            gsonBuilder.create()
        }
        factory<Interceptor> {
            val log = object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.d(REPOSITORY_TAG, message)
                }
            }

            HttpLoggingInterceptor(log)
        }
        single {
            OkHttpClient.Builder()
                    .connectTimeout(TIME_OUT_SECS, TimeUnit.SECONDS)
                    .writeTimeout(TIME_OUT_SECS, TimeUnit.SECONDS)
                    .readTimeout(TIME_OUT_SECS, TimeUnit.SECONDS)
                    .addInterceptor(get() as Interceptor)
                    .build()
        }

        single {
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(get() as OkHttpClient)
                    .addConverterFactory(
                            GsonConverterFactory.create(
                                    get() as Gson
                            )
                    )
                    .build()
        }

        single {
            OkHttp3Downloader(get() as OkHttpClient)
        }
    }

}
