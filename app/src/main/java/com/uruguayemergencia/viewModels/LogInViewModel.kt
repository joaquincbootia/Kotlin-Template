package com.uruguayemergencia.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uruguayemergencia.UruguayEmergencia
import com.uruguayemergencia.data.local.PreferenceDataStoreConstants.USER_TOKEN_KEY
import com.uruguayemergencia.data.local.PreferenceDataStoreHelper
import kotlinx.coroutines.launch

class LogInViewModel : ViewModel() {
    private var email: String? = null
    private var password: String? = null

    fun setEmailAndPassword(email: String, password: String) {
        this.email = email
        this.password = password
    }

    private fun saveTokenToDataStore(token: String) {
        val dataStore: PreferenceDataStoreHelper = UruguayEmergencia.preferenceDataStoreHelper
        viewModelScope.launch {
            dataStore.putPreference(USER_TOKEN_KEY, token)
        }
    }
}
