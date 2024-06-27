package com.uruguayemergencia.network.api.repositories

import com.uruguayemergencia.network.api.services.PostMockService
import com.uruguayemergencia.network.models.MockPost
import retrofit2.Response

class PostMockRepository(private val postMockService: PostMockService) {
    suspend fun fetchPostsData(): Response<MockPost> {
        return postMockService.fetchPostsData()
    }

}