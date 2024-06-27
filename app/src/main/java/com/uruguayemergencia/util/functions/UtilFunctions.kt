package com.uruguayemergencia.util.functions

import com.uruguayemergencia.data.local.PreferenceDataStoreConstants.USER_TOKEN_KEY
import com.uruguayemergencia.data.local.PreferenceDataStoreHelper


object UtilFunctions {
    suspend fun hasToken(dataStore: PreferenceDataStoreHelper): Boolean {
        val token = dataStore.getPreferenceValue(USER_TOKEN_KEY, "")
        return token.isNotEmpty()
    }
}