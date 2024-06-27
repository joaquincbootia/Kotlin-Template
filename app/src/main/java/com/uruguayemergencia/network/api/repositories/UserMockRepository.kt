package com.uruguayemergencia.network.api.repositories

import com.uruguayemergencia.network.api.services.UserMockService
import com.uruguayemergencia.network.models.MockUser
import retrofit2.Response

class UserMockRepository(private val userMockService: UserMockService) {
    suspend fun fetchUserData(): Response<MockUser> {
        return userMockService.fetchUserData()
    }

}