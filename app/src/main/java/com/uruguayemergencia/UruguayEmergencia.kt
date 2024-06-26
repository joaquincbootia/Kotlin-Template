package com.uruguayemergencia

import android.app.Application
import com.uruguayemergencia.network.NetworkModule
import com.uruguayemergencia.data.local.PreferenceDataStoreHelper
import com.uruguayemergencia.network.api.ApiService

class UruguayEmergencia : Application() {
    companion object {
        lateinit var apiService: ApiService
            private set
        lateinit var preferenceDataStoreHelper: PreferenceDataStoreHelper
            private set
    }

    override fun onCreate() {
        super.onCreate()
        NetworkModule.initRetrofit(applicationContext)
        apiService = NetworkModule.retrofit.create(ApiService::class.java)
        preferenceDataStoreHelper = PreferenceDataStoreHelper(this)
    }
}