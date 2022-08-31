package com.example.naviassignmentapp.domain.usecase

import androidx.paging.PagingSource
import com.example.naviassignmentapp.data.repository.PullRequestPagingDataSource
import com.example.naviassignmentapp.domain.model.PullRequestModel
import com.example.naviassignmentapp.domain.repository.IPullRequestRepository

class GetClosedPullRequestUseCaseImpl(private val repository: IPullRequestRepository) :
    IGetPullRequestUseCase {
    override fun perform(): PagingSource<Int, PullRequestModel> {
        return PullRequestPagingDataSource(repository)
    }
}