package com.uruguayemergencia.data.local

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface IPreferenceDataStoreAPI {
    suspend fun <T> getPreferenceFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T>
    suspend fun <T> getPreferenceValue(key: Preferences.Key<T>, defaultValue: T): T
    suspend fun <T> putPreference(key: Preferences.Key<T>, value: T)
    suspend fun <T> removePreference(key: Preferences.Key<T>)
    suspend fun <T> clearAllPreference()
}