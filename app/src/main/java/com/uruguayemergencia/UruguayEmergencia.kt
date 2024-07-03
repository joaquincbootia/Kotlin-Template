package com.uruguayemergencia

import android.app.Application
import com.uruguayemergencia.data.local.PreferenceDataStoreHelper
import com.uruguayemergencia.network.NetworkModule
import com.uruguayemergencia.network.UnauthorizedAccessHandler

class UruguayEmergencia : Application() {
    companion object {
        lateinit var preferenceDataStoreHelper: PreferenceDataStoreHelper
            private set
    }

    override fun onCreate() {
        super.onCreate()
        NetworkModule.initialize(this, object : UnauthorizedAccessHandler {
            override fun handleUnauthorizedAccess() {
            }
        })
        preferenceDataStoreHelper = PreferenceDataStoreHelper(this)
    }
}