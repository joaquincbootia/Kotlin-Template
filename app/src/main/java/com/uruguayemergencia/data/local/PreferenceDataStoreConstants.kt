package com.uruguayemergencia.data.local

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceDataStoreConstants {
    val USER_IS_LOG_IN = booleanPreferencesKey("USER_TOKEN_KEY")
    val USER_TOKEN_KEY = stringPreferencesKey("USER_TOKEN_KEY")
}