package com.example.naviassignmentapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.naviassignmentapp.domain.repository.IPullRequestRepository
import javax.inject.Inject

class PullRequestRemoteStore @Inject constructor(private val service: PullRequestService) :
    IPullRequestRepository {
    override fun getClosedPullRequests() =
        Pager(config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = { PullRequestPagingDataSource(service) }
        ).flow

}