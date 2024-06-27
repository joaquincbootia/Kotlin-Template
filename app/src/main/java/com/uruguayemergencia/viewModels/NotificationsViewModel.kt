package com.uruguayemergencia.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uruguayemergencia.network.api.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.uruguayemergencia.network.models.Result

class NotificationsViewModel(private val apiService: ApiService) : ViewModel() {
    private val _apiResponse = MutableStateFlow<Result<String>>(Result.Loading)
    val apiResponse: StateFlow<Result<String>> = _apiResponse

    init {
        fetchUserDataFromApi()
    }

    private fun fetchUserDataFromApi() {
        viewModelScope.launch {
            try {
                val response = apiService.fetchUserData()
                if (response.isSuccessful) {
                    _apiResponse.value = Result.Success(response.body().toString())
                } else {
                    _apiResponse.value = Result.Error("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                _apiResponse.value = Result.Error("Error: ${e.message}")
            }
        }
    }
}