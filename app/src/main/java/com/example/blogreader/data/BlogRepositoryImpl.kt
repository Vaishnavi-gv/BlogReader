package com.example.blogreader.data

import com.example.blogreader.data.model.BlogPost
import com.example.blogreader.data.web.BlogApi
import com.example.blogreader.domain.repositories.BlogRepository
import retrofit2.Response
import javax.inject.Inject

class BlogRepositoryImpl @Inject constructor(
    private val blogApi: BlogApi
):BlogRepository {

    override suspend fun getPosts(
        perPage: Int,
        page: Int
    ): Response<List<BlogPost>>
    {
        return blogApi.getPosts(perPage, page)
    }
}