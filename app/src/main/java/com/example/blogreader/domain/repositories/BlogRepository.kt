package com.example.blogreader.domain.repositories

import com.example.blogreader.data.model.BlogPost
import retrofit2.Response

interface BlogRepository {

    suspend fun getPosts(
        perPage: Int = 10,
        page: Int = 1
    ): Response<List<BlogPost>>
}