package com.example.naviassignmentapp.domain.usecase

import com.example.naviassignmentapp.domain.model.PullRequestState
import com.example.naviassignmentapp.domain.repository.IPullRequestRepository

class GetClosedPullRequestUseCaseImpl(private val repository: IPullRequestRepository) :
    IGetPullRequestUseCase {
    override suspend fun perform(): IGetPullRequestUseCase.Response {
        val state = PullRequestState.CLOSED.key
        val pageNo = 1
        val request = IPullRequestRepository.Request(state, pageNo)
        return when (val response = repository.getClosedPullRequests(request)) {
            is IPullRequestRepository.Response.Success ->
                IGetPullRequestUseCase.Response.Success(response.pullRequests)
            is IPullRequestRepository.Response.Failure ->
                IGetPullRequestUseCase.Response.Failure(response.error)
        }
    }
}