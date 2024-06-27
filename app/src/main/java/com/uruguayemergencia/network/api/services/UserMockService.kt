package com.uruguayemergencia.network.api.services

import com.uruguayemergencia.network.models.MockUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface UserMockService {
    @GET("todos/1")
    @Headers("Content-Type: application/json")
    suspend fun fetchUserData(): Response<MockUser>

}