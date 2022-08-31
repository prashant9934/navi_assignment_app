package com.example.naviassignmentapp.data.repository

import com.example.naviassignmentapp.data.model.PullRequestResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PullRequestService {
    @GET("repos/square/retrofit/pulls")
    suspend fun getPullRequest(
        @Query("state") state: String,
        @Query("page") pageNo: Int
    ): List<PullRequestResponse>
}