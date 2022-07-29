package com.mvclopes.spacexlaunches.data.service

import androidx.viewbinding.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiModule {

    private const val BASE_URL = "https://api.spacexdata.com/v3/"
    private const val READ_TIMEOUT = 20L
    private const val CONNECT_TIMEOUT = 20L
    private val log = HttpLoggingInterceptor()

    init {
        if (BuildConfig.DEBUG) log.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient
        .Builder()
        .addInterceptor(log)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .client(client)
        .baseUrl(BASE_URL)
        .build()

    val spaceXService: SpaceXService by lazy { retrofit.create(SpaceXService::class.java) }
}
