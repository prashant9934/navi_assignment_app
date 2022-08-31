package com.example.naviassignmentapp.domain.repository

import com.example.naviassignmentapp.domain.model.PullRequestModel
import java.lang.Exception

interface IPullRequestRepository {
    sealed class Response {
        class Success(val pullRequests: List<PullRequestModel>): Response()
        class Failure(val error: Exception): Response()
    }
    data class Request(val state: String, val pageNo: Int)

    suspend fun getClosedPullRequests(request: Request): Response
}