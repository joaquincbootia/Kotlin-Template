package com.uruguayemergencia.network.api.services

import com.uruguayemergencia.network.models.MockPost
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface PostMockService {
    @GET("posts/1")
    @Headers("Content-Type: application/json")
    suspend fun fetchPostsData(): Response<MockPost>

}