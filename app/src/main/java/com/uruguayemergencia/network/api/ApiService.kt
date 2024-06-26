package com.uruguayemergencia.network.api

import com.uruguayemergencia.network.models.MockPost
import com.uruguayemergencia.network.models.MockUser
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("todos/1")
    suspend fun fetchUserData(): Response<MockUser>

    @GET("posts/1")
    suspend fun fetchPostsData(): Response<MockPost>
}