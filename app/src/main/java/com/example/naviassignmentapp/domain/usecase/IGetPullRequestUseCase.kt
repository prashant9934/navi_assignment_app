package com.example.naviassignmentapp.domain.usecase

import com.example.naviassignmentapp.domain.model.PullRequestModel
import java.lang.Exception

interface IGetPullRequestUseCase {
    sealed class Response{
        class Success(val pullRequests: List<PullRequestModel>): Response()
        class Failure(val error: Exception): Response()
    }
    suspend fun perform(): Response
}