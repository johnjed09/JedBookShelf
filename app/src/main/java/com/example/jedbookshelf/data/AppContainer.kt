package com.example.jedbookshelf.data

import com.example.jedbookshelf.network.JedBookShelfApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


interface AppContainer {
    val jedBookShelfRepository: JedBookShelfRepository
}

class DefaultAppContainer : AppContainer {
    private val jsonConfiguration = Json { ignoreUnknownKeys = true }

    private val BASE_URL = "https://www.googleapis.com"

    private val retrofit: Retrofit = Retrofit.Builder()
//        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(jsonConfiguration.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: JedBookShelfApiService by lazy {
        retrofit.create(JedBookShelfApiService::class.java)
    }

    override val jedBookShelfRepository: JedBookShelfRepository by lazy {
        DefaultJedBookShelfRepository(retrofitService)
    }
}