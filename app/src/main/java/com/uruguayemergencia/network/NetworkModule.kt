package com.uruguayemergencia.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.uruguayemergencia.util.constants.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkModule {

    private lateinit var chuckerCollector: ChuckerCollector
    private lateinit var chuckerInterceptor: ChuckerInterceptor
    lateinit var retrofit: Retrofit
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun initRetrofit(context: Context) {
        chuckerCollector = ChuckerCollector(context)
        chuckerInterceptor = ChuckerInterceptor.Builder(context)
            .collector(chuckerCollector)
            .maxContentLength(250_000L)
            .redactHeaders("Authorization")
            .alwaysReadResponseBody(true)
            .build()

        // Build OkHttpClient with the chuckerInterceptor
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            // Other OkHttpClient configuration...
            .build()

        // Build retrofit with the configured OkHttpClient
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // Add the configured OkHttpClient
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

}