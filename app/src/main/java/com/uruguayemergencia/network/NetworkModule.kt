package com.uruguayemergencia.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.uruguayemergencia.util.constants.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.uruguayemergencia.data.local.PreferenceDataStoreConstants.USER_TOKEN_KEY
import com.uruguayemergencia.network.api.services.PostMockService
import com.uruguayemergencia.network.api.services.UserMockService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface UnauthorizedAccessHandler {
    fun handleUnauthorizedAccess()
}

object NetworkModule {

    private lateinit var appContext: Context
    private lateinit var unauthorizedAccessHandler: UnauthorizedAccessHandler

    private lateinit var okHttpClient: OkHttpClient
    private lateinit var retrofit: Retrofit

    fun initialize(context: Context, handler: UnauthorizedAccessHandler) {
        appContext = context
        unauthorizedAccessHandler = handler
        setupNetwork()
    }

    fun updateUnauthorizedAccessHandler(handler: UnauthorizedAccessHandler) {
        unauthorizedAccessHandler = handler
    }

    private fun setupNetwork() {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val requestBuilder = originalRequest.newBuilder()
                val token = USER_TOKEN_KEY
                if (token != null) {
                    requestBuilder.addHeader("Authorization", "Bearer $token")
                }
                val request = requestBuilder.build()
                val response = chain.proceed(request)
                if (response.code == 401) {
                    unauthorizedAccessHandler.handleUnauthorizedAccess()
                }
                response
            }
            .addInterceptor(
                ChuckerInterceptor.Builder(appContext)
                    .collector(ChuckerCollector(appContext))
                    .maxContentLength(250_000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(true)
                    .build()
            )
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    val userMockService: UserMockService by lazy {
        retrofit.create(UserMockService::class.java)
    }

    val postMockService: PostMockService by lazy {
        retrofit.create(PostMockService::class.java)
    }
}